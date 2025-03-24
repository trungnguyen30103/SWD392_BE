package com.blindbox.request.Create.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageCreateRequest {

    @NotNull
    Integer productID;

    @NotBlank
    String imageUrl;

    String altText;
}
