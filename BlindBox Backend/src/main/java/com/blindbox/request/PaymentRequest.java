package com.blindbox.request;

//public class PaymentRequest {
//    private Integer userId;
//    private Integer orderId;
//    private double amount;
//    private String callbackUrl;
//
//    // Getters and Setters
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public String getCallbackUrl() {
//        return callbackUrl;
//    }
//
//    public void setCallbackUrl(String callbackUrl) {
//        this.callbackUrl = callbackUrl;
//    }
//}
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Integer userId;
    private Integer orderId;
    private BigDecimal amount;
    private String paymentMethod;
}