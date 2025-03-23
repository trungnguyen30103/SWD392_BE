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
    private String blindboxName;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Integer categoryID;

    private List<BlindboxImageCreateRequest> blindboxImages;

}
