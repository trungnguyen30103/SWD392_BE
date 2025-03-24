package com.blindbox.request.Create.Blindbox;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxItemCreateRequest {

    @NotNull
    Integer blindboxID;

    @NotBlank
    String name;

    @NotNull
    Integer rarity;

}
