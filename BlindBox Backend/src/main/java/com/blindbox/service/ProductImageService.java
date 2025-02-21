package com.blindbox.service;

import com.blindbox.model.ProductImage;
import java.util.List;

public interface ProductImageService {
    List<ProductImage> getAllProductImages();
    List<ProductImage> getImagesByProductId(Integer productId);
    ProductImage getProductImageById(Integer id);
    ProductImage createProductImage(ProductImage productImage);
    void deleteProductImage(Integer id);
}
