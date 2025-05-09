package com.blindbox.request.Update.Product;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageUpdateRequest {

    Integer productImageID;
    String imageUrl;
    String altText;

}
