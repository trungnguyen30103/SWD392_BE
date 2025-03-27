package com.blindbox.service.impl;

import com.blindbox.enums.DiscountStatus;
import com.blindbox.enums.OrderStatus;
import com.blindbox.enums.PaymentStatus;
import com.blindbox.enums.ShippingStatus;
import com.blindbox.model.*;
import com.blindbox.repository.*;
import com.blindbox.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

    private final DiscountRepository discountRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, DiscountRepository discountRepository, OrderDetailRepository orderDetailRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.discountRepository = discountRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public Order updateOrder(Integer orderId, Order order) {
        if (orderRepository.existsById(orderId)) {
            order.setOrderId(orderId);
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    // Quy's modify

    // Create Buy product order from cart
    @Override
    @Transactional
    public Order createOrderFromCart(Integer userId) {
        // Fetch the cart for the user
        Cart cart = cartRepository.findByUser_UserID(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));

        // Create a new order
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setShippingStatus(ShippingStatus.PENDING);
        order.setOrderDetails(new ArrayList<>());

        // Transfer cart items to order details
        for (CartItem cartItem : cart.getCartItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(calculateOrderDetailPrice(orderDetail));

            order.getOrderDetails().add(orderDetail);
        }

        order.setTotalAmount(calculateOrderTotalAmount(order.getOrderDetails()));

        // Save the order and order details
        orderRepository.save(order);

        return order;
    }

    // Pay by User's/Customer's balance
    @Override
    @Transactional
    public boolean payForOrderByUserBalance(Integer userId, Integer orderId) {
        // Fetch the user and order
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + userId));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found for id: " + orderId));

        // Check if the user's balance is sufficient
        if (user.getBalance() >= order.getTotalAmount()) {
            // Deduct the balance
            user.setBalance(user.getBalance() - order.getTotalAmount());
            userRepository.save(user);

            // Update the order status
            order.setStatus(OrderStatus.PAID);
            order.setPaymentStatus(PaymentStatus.PAID);
            orderRepository.save(order);

            // Clear the cart
            Cart cart = cartRepository.findByUser_UserID(userId)
                    .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));
            cart.getCartItems().clear();
            cart.setTotalAmount(0.0);
            cartRepository.save(cart);

            return true;
        } else {
            return false;
        }
    }

    // Calculate OrderDetail Price
    private Double calculateOrderDetailPrice(OrderDetail orderDetail) {
        Product product = orderDetail.getProduct();
        Optional<Discount> discountOpt = discountRepository.findByStatusAndProduct_ProductID(DiscountStatus.ACTIVE, product.getProductID());
        if (discountOpt.isPresent()) {
            Discount discount = discountOpt.get();
            return product.getPrice() * (1 - discount.getDiscountPercentage() / 100) * orderDetail.getQuantity();
        } else {
            return product.getPrice() * orderDetail.getQuantity();
        }
    }

    // Calculate Order Total Amount
    private Double calculateOrderTotalAmount(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .mapToDouble(OrderDetail::getPrice)
                .sum();
    }
}
