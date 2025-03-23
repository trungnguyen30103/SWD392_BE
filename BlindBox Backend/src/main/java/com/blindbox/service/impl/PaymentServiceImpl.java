package com.blindbox.service.impl;

import com.blindbox.model.Payment;
import com.blindbox.model.Order;
import com.blindbox.model.User;
import com.blindbox.repository.PaymentRepository;
import com.blindbox.repository.OrderRepository;
import com.blindbox.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment processPayment(User user, Order order, BigDecimal amount, String paymentMethod) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus("Pending");  // Trạng thái ban đầu
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setCreatedAt(LocalDateTime.now());

        // Lưu thanh toán vào DB
        Payment savedPayment = paymentRepository.save(payment);

        // Cập nhật trạng thái thanh toán
        savedPayment.setStatus("Completed");
        return paymentRepository.save(savedPayment);
    }
}
