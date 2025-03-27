package com.blindbox.request.Create.Order.Blindbox;

import com.blindbox.enums.GachaType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderBlindboxCreateRequest {

    @NotNull
    Integer userID;

    Integer blindboxID;

    GachaType gachaType;

}
