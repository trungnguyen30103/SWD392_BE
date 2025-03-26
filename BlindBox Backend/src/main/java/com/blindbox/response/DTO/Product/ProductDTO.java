package com.blindbox.response.DTO.Product;

import com.blindbox.model.CartItem;
import com.blindbox.model.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    Integer productId;
    String productName;
    double price;
    List<ProductImageDTO> productImageDTOS;

    public ProductDTO(Product product) {
        this.productId = product.getProductID();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productImageDTOS = product.getProductImages().stream()
                .map(ProductImageDTO::new)
                .collect(Collectors.toList());
    }
}

