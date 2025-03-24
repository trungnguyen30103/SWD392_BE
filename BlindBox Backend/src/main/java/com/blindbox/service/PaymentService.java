package com.blindbox.service;

import com.blindbox.model.User;
import com.blindbox.model.Order;
import com.blindbox.model.Payment;



public interface PaymentService {
    Payment processPayment(User user, Order order, double amount, String paymentMethod);


}
