package com.blindbox.request.Create.Order.Blindbox;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailBlindboxCreateRequest {

    Integer orderID;
    Integer blindboxID;
    Integer quantity;
    double price;

}
