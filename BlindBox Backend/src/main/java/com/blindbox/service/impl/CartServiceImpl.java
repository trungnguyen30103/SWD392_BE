package com.blindbox.service.impl;

import com.blindbox.enums.PaymentStatus;
import com.blindbox.model.Cart;
import com.blindbox.model.CartItem;
import com.blindbox.model.Order;
import com.blindbox.model.Product;
import com.blindbox.repository.CartItemRepository;
import com.blindbox.repository.CartRepository;
import com.blindbox.repository.OrderRepository;
import com.blindbox.repository.ProductRepository;
import com.blindbox.response.DTO.Cart.CartDTO;
import com.blindbox.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    // Add product to cart
    @Override
    public void addProductToCart(Integer userID, Integer productID, int quantity) {
        checkPendingOrder(userID);
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the item  already exists in cart
        boolean productExists = false;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getProductID().equals(productID)) {
                // Update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setPrice(calculateItemTotalAmount(cart.getCartId(), productID));
                cartItemRepository.save(cartItem);
                productExists = true;
                break;
            }
        }

        // If the item does not exist, add it to the cart
        if (!productExists) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setQuantity(quantity);
            item.setProduct(product);
            item.setPrice(product.getPrice() * quantity);
            cartItemRepository.save(item);
            cart.getCartItems().add(item);
        }

        // Update the total amount of the cart
        cart.setTotalAmount(calculateCartTotalAmount(cart.getCartItems()));
        cartRepository.save(cart);
    }

    // Get User's cart
    @Override
    @Transactional
    public CartDTO getCartByUserID(Integer userID) {
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return new CartDTO(cart);
    }

    // Remove a product from cart
    @Override
    @Transactional
    public void removeProductFromCart(Integer userID, Integer productID) {
        checkPendingOrder(userID);
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findByCart_CartIdAndProduct_ProductID(cart.getCartId(), productID)
                .orElseThrow(() -> new RuntimeException("Product not exists in your cart"));
        cartItemRepository.delete(cartItem);

        // Update the total amount of the cart
        Cart updatedCart = cartRepository.findById(cart.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        updatedCart.setTotalAmount(calculateCartTotalAmount(updatedCart.getCartItems()));
        cartRepository.save(updatedCart);
    }

    // Clear entire Cart
    @Override
    public void clearCart(Integer userID) {
        checkPendingOrder(userID);
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().clear();
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);

        List<CartItem> items = cartItemRepository.findByCart_CartId(cart.getCartId());
        cartItemRepository.deleteAll(items);
    }

    // Update Cart Item
    @Override
    public void updateCartItem(Integer userID, Integer productID, int quantity) {
        checkPendingOrder(userID);
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findByCart_CartIdAndProduct_ProductID(cart.getCartId(), productID)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity == 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice() * quantity);
            cartItemRepository.save(cartItem);
        }
        List<CartItem> updatedCartItems = cartItemRepository.findByCart_CartId(cart.getCartId());
        cart.setTotalAmount(updatedCartItems.stream().mapToDouble(CartItem::getPrice).sum());
        cartRepository.save(cart);
    }

    /* Calculator */
    // Calculate Item Total Amount
    private Double calculateItemTotalAmount(Integer cartID, Integer productID) {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem item = cartItemRepository.findByCart_CartIdAndProduct_ProductID(cartID, productID)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        return product.getPrice() * item.getQuantity();
    }

    // Calculate Cart Total Amount
    private Double calculateCartTotalAmount(List<CartItem> items) {
        return items.stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
    }

    private void checkPendingOrder(Integer userID) {
        Optional<Order> pendingOrders = orderRepository.findByUser_UserIDAndPaymentStatus(userID, PaymentStatus.PENDING);
        if (pendingOrders.isPresent()) {
            throw new RuntimeException("Cannot modify cart while there is a pending order.");
        }
    }
}
