package com.blindbox.request.Update.Blindbox;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxItemUpdateRequest {

    Integer blindboxItemID;
    Integer blindboxID;
    String name;
    Integer rarity;

}
