package com.blindbox.service;

import com.blindbox.model.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Integer id);
    Payment createPayment(Payment payment);
    Payment updatePayment(Integer id, Payment payment);
    void deletePayment(Integer id);
    //Payment getPaymentByTransactionId(String transactionId);
}
