package com.blindbox.request.Create.Blindbox;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxCreateRequest {

    @NotBlank
    String blindboxName;

    String description;

    @NotNull
    @DecimalMin("0.0")
    Double price;

    @NotNull
    @Min(0)
    Integer stock;

    @NotNull
    Integer categoryID;

    List<BlindboxImageCreateRequest> blindboxImages;

}
