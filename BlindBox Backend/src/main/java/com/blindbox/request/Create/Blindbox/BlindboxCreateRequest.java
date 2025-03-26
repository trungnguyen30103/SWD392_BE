package com.blindbox.request.Create.Blindbox;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxCreateRequest {

    @NotBlank
    String name;

    String description;

    @NotNull
    @DecimalMin("0.0")
    Double price;

    @NotNull
    Integer categoryID;

    Set<BlindboxImageCreateRequest> blindboxImages;

    Set<BlindboxItemCreateRequest> blindboxItem;

}
