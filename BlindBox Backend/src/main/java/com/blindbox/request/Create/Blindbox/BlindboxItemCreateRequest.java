package com.blindbox.request.Create.Blindbox;

import com.blindbox.enums.Blindbox.Rarity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxItemCreateRequest {

    @NotBlank
    String name;

    @NotNull
    Rarity rarity;

    Integer stock;

    @NotNull
    String imageUrl;

}
