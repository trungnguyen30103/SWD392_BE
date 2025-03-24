package com.blindbox.request.Update.Blindbox;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxUpdateRequest {

    String blindboxName;

    String description;

    Double price;

    Integer stock;

    Integer categoryID;

    List<BlindboxImageUpdateRequest> blindboxImages;

    List<BlindboxItemUpdateRequest> blindboxItem;

}
