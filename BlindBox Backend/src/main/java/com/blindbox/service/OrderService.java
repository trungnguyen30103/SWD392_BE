package com.blindbox.service;

import com.blindbox.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Integer orderID);

    void deleteOrder(Integer orderID);
}
