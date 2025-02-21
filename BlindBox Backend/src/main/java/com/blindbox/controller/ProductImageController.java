package com.blindbox.controller;

import com.blindbox.model.ProductImage;
import com.blindbox.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productimages")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public List<ProductImage> getAllProductImages() {
        return productImageService.getAllProductImages();
    }

    @GetMapping("/product/{productID}")
    public List<ProductImage> getImagesByProductId(@PathVariable Integer productId) {
        return productImageService.getImagesByProductId(productId);
    }

    @GetMapping("/{id}")
    public ProductImage getProductImageById(@PathVariable Integer id) {
        return productImageService.getProductImageById(id);
    }

    @PostMapping
    public ProductImage createProductImage(@RequestBody ProductImage productImage) {
        return productImageService.createProductImage(productImage);
    }

    @DeleteMapping("/{id}")
    public void deleteProductImage(@PathVariable Integer id) {
        productImageService.deleteProductImage(id);
    }
}
