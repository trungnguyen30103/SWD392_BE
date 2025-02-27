package com.blindbox.service.impl;

import com.blindbox.model.Order;
import com.blindbox.model.Payment;
import com.blindbox.model.User;
import com.blindbox.repository.OrderRepository;
import com.blindbox.repository.PaymentRepository;
import com.blindbox.repository.UserRepository;
import com.blindbox.request.PaymentRequest;
import com.blindbox.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Integer id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.orElse(null);
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Integer id, Payment payment) {
        if (paymentRepository.existsById(id)) {
            payment.setPaymentId(id);
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }



//    private static final String BANK_PAYMENT_API_URL = "https://bank-api.com/pay"; // Example API URL
//
//    public String initiatePayment(Long userId, Long orderId, double amount) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        // Create a payment request to bank API
//        String paymentUrl = sendPaymentRequestToBank(user, order, amount);
//
//        return paymentUrl; // Frontend should redirect user to this URL
//    }
//
//    private String sendPaymentRequestToBank(User user, Order order, double amount) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        PaymentRequest paymentRequest = new PaymentRequest();
//        paymentRequest.setUserId(user.getUserID());
//        paymentRequest.setOrderId(order.getOrderID());
//        paymentRequest.setAmount(amount);
//        paymentRequest.setCallbackUrl("https://your-server.com/api/payment/callback"); // Webhook URL
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);
//        ResponseEntity<Map> response = restTemplate.postForEntity(BANK_PAYMENT_API_URL, requestEntity, Map.class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            return response.getBody().get("paymentUrl").toString(); // Redirect user here
//        } else {
//            throw new RuntimeException("Failed to create payment request");
//        }
//    }
//
//
//    @Override
//    public Payment getPaymentByTransactionId(String transactionId) {
//        return null;
//    }
}
