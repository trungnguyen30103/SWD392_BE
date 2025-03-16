package com.blindbox.controller;

import com.blindbox.model.User;
import com.blindbox.model.Order;
import com.blindbox.repository.OrderRepository;
import com.blindbox.repository.UserRepository;
import com.blindbox.service.PaymentService;
import com.blindbox.controller.PaymentRequest;
import com.blindbox.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    // API thanh toán
    @PostMapping("/process")
    public Payment processPayment(@RequestBody PaymentRequest request) {
        // Lấy thông tin người dùng (từ JWT hoặc session, giả sử là userId)
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy thông tin đơn hàng (Order)
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));

        // Xử lý thanh toán
        return paymentService.processPayment(user, order, request.getAmount(), request.getPaymentMethod());
    }
}
