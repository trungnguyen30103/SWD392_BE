package com.blindbox.request.Create.Order.Product;

import com.blindbox.enums.GachaType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderProductCreateRequest {

    @NotNull
    Integer userID;

    List<OrderDetailProductCreateRequest> orderDetailProductCreateRequests;

}
