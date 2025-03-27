package com.blindbox.request.Update.Blindbox;

import com.blindbox.enums.Blindbox.Rarity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxItemUpdateRequest {

    Integer blindboxItemID;
    String name;
    Rarity rarity;
    Integer stock;
    String imageUrl;

}
