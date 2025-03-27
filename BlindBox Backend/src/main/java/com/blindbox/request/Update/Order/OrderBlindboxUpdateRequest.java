package com.blindbox.request.Update.Order;

import com.blindbox.request.Create.Order.Blindbox.OrderDetailBlindboxCreateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderBlindboxUpdateRequest {

    @NotNull
    Integer orderId;

    @NotNull
    Integer userID;

    List<OrderDetailBlindboxCreateRequest> orderDetailBlindboxCreateRequests;

}
