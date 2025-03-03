package com.blindbox.service;

import com.blindbox.model.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> getAllProductImages();
    ProductImage getProductImageById(Integer id);
    ProductImage createProductImage(ProductImage productImage);
    ProductImage updateProductImage(Integer id, ProductImage productImage);
    boolean deleteProductImage(Integer id);
}
