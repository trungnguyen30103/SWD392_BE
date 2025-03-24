package com.blindbox.controller;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentRequest {

    private Integer userId;
    private Integer orderId;
    private Double amount;
    private String paymentMethod;

}
