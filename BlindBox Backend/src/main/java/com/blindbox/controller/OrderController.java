package com.blindbox.controller;

import com.blindbox.model.Order;
import com.blindbox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Tạo đơn hàng mới
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Lấy tất cả đơn hàng
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Lấy đơn hàng theo ID
    @GetMapping("/{orderID}")
    public Optional<Order> getOrderById(@PathVariable Integer orderID) {
        return orderService.getOrderById(orderID);
    }

    // Xóa đơn hàng theo ID
    @DeleteMapping("/{orderID}")
    public void deleteOrder(@PathVariable Integer orderID) {
        orderService.deleteOrder(orderID);
    }
}
