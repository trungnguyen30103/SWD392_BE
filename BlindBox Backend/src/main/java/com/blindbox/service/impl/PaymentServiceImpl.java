package com.blindbox.service.impl;

import com.blindbox.model.User;
import com.blindbox.model.Order;
import com.blindbox.model.Payment;
import com.blindbox.repository.PaymentRepository;
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

    // Phương thức xử lý thanh toán
    @Override
    public Payment processPayment(User user, Order order, BigDecimal amount, String paymentMethod) {
        // Kiểm tra tính hợp lệ của số tiền
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }

        // Tạo đối tượng Payment
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus("Pending");  // Trạng thái ban đầu là Pending
        payment.setTransactionId(UUID.randomUUID().toString());  // Tạo transactionId ngẫu nhiên
        payment.setCreatedAt(LocalDateTime.now());

        // Lưu vào cơ sở dữ liệu
        Payment savedPayment = paymentRepository.save(payment);

        // Sau khi thanh toán thành công, cập nhật trạng thái
        savedPayment.setStatus("Completed");

        // Cập nhật thông tin thanh toán vào cơ sở dữ liệu
        return paymentRepository.save(savedPayment);
    }
}
