package com.blindbox.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private Integer userId;
    private Integer orderId;
    private double amount;  // Using double for the amount
    private String paymentMethod;
}
