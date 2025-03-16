package com.blindbox.service;

import com.blindbox.model.User;
import com.blindbox.model.Order;
import com.blindbox.model.Payment;
import java.math.BigDecimal;

public interface PaymentService {
    Payment processPayment(User user, Order order, BigDecimal amount, String paymentMethod);
}
