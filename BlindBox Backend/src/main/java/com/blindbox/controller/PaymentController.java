package com.blindbox.controller;

import com.blindbox.model.User;
import com.blindbox.model.Order;
import com.blindbox.repository.OrderRepository;
import com.blindbox.repository.UserRepository;
import com.blindbox.service.PaymentService;
import com.blindbox.model.Payment;
import com.blindbox.request.PaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment Management System", description = "Operations pertaining to payments in the Payment Management System")
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
    @Operation(summary = "Process a payment", description = "Process a payment for an order")
    @PostMapping("/process")
    public Payment processPayment(@RequestBody PaymentRequest request) {
        // Lấy thông tin người dùng (từ JWT hoặc session, giả sử là userId)
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy thông tin đơn hàng (Order)
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));

        // Xử lý thanh toán, truyền amount kiểu double
        return paymentService.processPayment(user, order, request.getAmount(), request.getPaymentMethod());
    }
}
