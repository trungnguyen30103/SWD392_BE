package com.blindbox.service.impl;

import com.blindbox.enums.Blindbox.Rarity;
import com.blindbox.enums.DiscountStatus;
import com.blindbox.enums.GachaType;
import com.blindbox.enums.PaymentStatus;
import com.blindbox.model.*;
import com.blindbox.repository.*;
import com.blindbox.service.GachaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class GachaServiceImpl implements GachaService {

    private final BlindBoxItemRepository blindBoxItemRepository;

    private final BlindboxRepository blindboxRepository;

    private final GachaHistoryRepository gachaHistoryRepository;

    private final OrderRepository orderRepository;

    private final DiscountRepository discountRepository;

    private final ResultRepository resultRepository;

    private final Random random = new Random();

    public GachaServiceImpl(BlindBoxItemRepository blindBoxItemRepository, BlindboxRepository blindboxRepository, GachaHistoryRepository gachaHistoryRepository, OrderRepository orderRepository, DiscountRepository discountRepository, ResultRepository resultRepository) {
        this.blindBoxItemRepository = blindBoxItemRepository;
        this.blindboxRepository = blindboxRepository;
        this.gachaHistoryRepository = gachaHistoryRepository;
        this.orderRepository = orderRepository;
        this.discountRepository = discountRepository;
        this.resultRepository = resultRepository;
    }

    // Open blind box
    @Override
    @Transactional
    public String openBlindbox(Integer userId, Integer orderId) {
        // Fetch the user and order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found for id: " + orderId));

        // Check if the order is paid
        if (order.getPaymentStatus() != PaymentStatus.PAID) {
            return "Order is not paid!";
        }

        // Fetch the blindbox and its items
        Blindbox blindbox = order.getBlindbox();
        List<BlindBoxItem> items = blindBoxItemRepository.findAllByBlindbox_BlindboxID(blindbox.getBlindboxID());

        // Perform gacha based on gachaType
        int gachaTimes = order.getGachaType() == GachaType.FIVE_TIME ? 5 : 1;
        List<BlindBoxItem> wonItems = new ArrayList<>();
        for (int i = 0; i < gachaTimes; i++) {
            BlindBoxItem wonItem = performGacha(items);
            if (wonItem != null) {
                wonItems.add(wonItem);
                // Reduce stock
                wonItem.setStock(wonItem.getStock() - 1);
                blindbox.setTotalStock(blindbox.getTotalStock() - 1);
                blindBoxItemRepository.save(wonItem);
                blindboxRepository.save(blindbox);
            }
        }

        // Save won items to GachaHistory and Result
        for (BlindBoxItem item : wonItems) {
            GachaHistory history = new GachaHistory(userId, item.getName(), LocalDateTime.now().toString());
            gachaHistoryRepository.save(history);

            Result result = new Result();
            result.setOrder(order);
            result.setBlindboxItem(item);
            result.setCreatedAt(LocalDateTime.now());
            resultRepository.save(result);
        }

        // Update order details
        updateOrderDetails(order, wonItems);

        // Recalculate total price
        order.setTotalAmount(calculateOrderTotalAmount(order.getOrderDetails()));
        orderRepository.save(order);

        return "Gacha completed!";
    }


    // Perform gacha
    private BlindBoxItem performGacha(List<BlindBoxItem> items) {
        int randomValue = random.nextInt(100) + 1;
        List<BlindBoxItem> potentialItems = new ArrayList<>();

        for (BlindBoxItem item : items) {
            int chance = getChanceByRarity(item.getRarity());
            if (randomValue <= chance) {
                potentialItems.add(item);
            }
        }

        if (!potentialItems.isEmpty()) {
            return potentialItems.get(random.nextInt(potentialItems.size()));
        }

        // If no items meet the criteria, return a random item from the list
        return items.get(random.nextInt(items.size()));
    }

    // Get the chance
    private int getChanceByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> 80;
            case RARE -> 60;
            case EPIC -> 25;
            case LEGENDARY -> 1;
        };
    }

    // Update orderDetail(s)
    private void updateOrderDetails(Order order, List<BlindBoxItem> wonItems) {
        Map<Integer, OrderDetail> orderDetailMap = new HashMap<>();
        for (OrderDetail detail : order.getOrderDetails()) {
            orderDetailMap.put(detail.getBlindBoxItem().getBlindboxItemID(), detail);
        }

        for (BlindBoxItem item : wonItems) {
            OrderDetail detail = orderDetailMap.get(item.getBlindboxItemID());
            if (detail == null) {
                detail = new OrderDetail();
                detail.setOrder(order);
                detail.setBlindBoxItem(item);
                detail.setQuantity(1);
                detail.setPrice(calculateOrderDetailPrice(detail));
                order.getOrderDetails().add(detail);
                orderDetailMap.put(item.getBlindboxItemID(), detail);
            } else {
                detail.setQuantity(detail.getQuantity() + 1);
                detail.setPrice(calculateOrderDetailPrice(detail));
            }
        }
    }

    // Calculate OrderDetail Price
    private Double calculateOrderDetailPrice(OrderDetail orderDetail) {
        BlindBoxItem item = orderDetail.getBlindBoxItem();
        Optional<Discount> discountOpt = discountRepository.findByStatusAndProduct_ProductID(DiscountStatus.ACTIVE, item.getBlindbox().getBlindboxID());
        if (discountOpt.isPresent()) {
            Discount discount = discountOpt.get();
            return item.getBlindbox().getPrice() * (1 - discount.getDiscountPercentage() / 100) * orderDetail.getQuantity();
        } else {
            return item.getBlindbox().getPrice() * orderDetail.getQuantity();
        }
    }

    // Calculate Order Total Amount
    private Double calculateOrderTotalAmount(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .mapToDouble(OrderDetail::getPrice)
                .sum();
    }
}
