package com.blindbox.request.Create.Order.Product;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailProductCreateRequest {

    Integer orderID;
    Integer productID;
    Integer quantity;
    double price;

}
