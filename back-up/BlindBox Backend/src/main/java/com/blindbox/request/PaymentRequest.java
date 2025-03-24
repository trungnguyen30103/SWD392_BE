package com.blindbox.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequest {
    private Integer userId;
    private Integer orderId;
    private Double amount;
    private String callbackUrl;
}
