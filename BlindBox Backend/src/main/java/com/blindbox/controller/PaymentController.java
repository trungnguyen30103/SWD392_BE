package com.blindbox.controller;

import com.blindbox.model.Payment;
import com.blindbox.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Integer id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Integer id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
    }
}
