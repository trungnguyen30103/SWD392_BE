package com.blindbox.controller;

import com.blindbox.model.OrderDetail;
import com.blindbox.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Tạo chi tiết đơn hàng mới
    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.createOrderDetail(orderDetail);
    }

    // Lấy tất cả chi tiết đơn hàng
    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    // Lấy chi tiết đơn hàng theo ID
    @GetMapping("/{orderDetailID}")
    public Optional<OrderDetail> getOrderDetailById(@PathVariable Long orderDetailID) {
        return orderDetailService.getOrderDetailById(orderDetailID);
    }

    // Xóa chi tiết đơn hàng theo ID
    @DeleteMapping("/{orderDetailID}")
    public void deleteOrderDetail(@PathVariable Long orderDetailID) {
        orderDetailService.deleteOrderDetail(orderDetailID);
    }
}
