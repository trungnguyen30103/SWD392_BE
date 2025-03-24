package com.blindbox.request.Update.Product;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {

    String productName;
    String description;
    Double price;
    Integer stock;
    Integer categoryID;
    List<ProductImageUpdateRequest> productImages;
}
