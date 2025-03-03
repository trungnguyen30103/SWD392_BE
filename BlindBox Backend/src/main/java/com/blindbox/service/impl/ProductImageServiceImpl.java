package com.blindbox.service.impl;

import com.blindbox.model.ProductImage;
import com.blindbox.repository.ProductImageRepository;
import com.blindbox.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage getProductImageById(Integer id) {
        Optional<ProductImage> productImage = productImageRepository.findById(id);
        return productImage.orElse(null);
    }

    @Override
    public ProductImage createProductImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    @Override
    public ProductImage updateProductImage(Integer id, ProductImage productImage) {
        if (productImageRepository.existsById(id)) {
            productImage.setProductImageId(id);
            return productImageRepository.save(productImage);
        }
        return null;
    }

    @Override
    public boolean deleteProductImage(Integer id) {
        if (productImageRepository.existsById(id)) {
            productImageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
