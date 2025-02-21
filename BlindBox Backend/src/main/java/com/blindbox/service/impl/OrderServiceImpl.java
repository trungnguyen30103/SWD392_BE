package com.blindbox.service.impl;

import com.blindbox.model.Order;
import com.blindbox.repository.OrderRepository;
import com.blindbox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Integer orderID) {
        return orderRepository.findById(orderID);
    }

    @Override
    public void deleteOrder(Integer orderID) {
        orderRepository.deleteById(orderID);
    }
}
