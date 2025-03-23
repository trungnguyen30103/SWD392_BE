package com.blindbox.request.Create.Blindbox;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindboxImageCreateRequest {

    @NotBlank
    private String imageUrl;

    @NotNull
    private Integer blindboxID;
}