package com.blindbox.service;

import com.blindbox.model.ProductImage;
import java.util.List;

public interface ProductImageService {
    List<ProductImage> getAllProductImages();
    List<ProductImage> getImagesByProductId(Long productId);
    ProductImage getProductImageById(Long id);
    ProductImage createProductImage(ProductImage productImage);
    void deleteProductImage(Long id);
}
