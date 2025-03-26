package com.blindbox.response.DTO.Product;

import com.blindbox.model.ProductImage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageDTO {
    String imageUrl;
    String altText;

    public ProductImageDTO(ProductImage image) {
        this.imageUrl = image.getImageUrl();
        this.altText = image.getAltText();
    }
}
