package com.blindbox.request.Update.Product;

import com.blindbox.enums.Blindbox.ProductStatus;
import com.blindbox.request.Create.Product.ProductImageCreateRequest;
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
    ProductStatus status;
    Integer stock;
    Integer categoryID;
    List<ProductImageUpdateRequest> productImageUpdateRequests;
    List<ProductImageCreateRequest> productImageCreateRequests;
}
