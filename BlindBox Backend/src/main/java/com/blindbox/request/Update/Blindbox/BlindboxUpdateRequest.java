package com.blindbox.request.Update.Blindbox;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxUpdateRequest {

    String blindboxName;

    String description;

    Double price;

    Integer stock;

    Integer categoryID;

    Set<BlindboxImageUpdateRequest> blindboxImages;

    Set<BlindboxItemUpdateRequest> blindboxItem;

}
