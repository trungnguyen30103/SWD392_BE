package com.blindbox.request.Create.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageCreateRequest {

    @NotBlank
    String imageUrl;

    String altText;
}
