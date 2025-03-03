package com.blindbox.controller;

import com.blindbox.model.ProductImage;
import com.blindbox.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    // Lấy tất cả ProductImage
    @GetMapping
    public ResponseEntity<List<ProductImage>> getAllProductImages() {
        List<ProductImage> productImages = productImageService.getAllProductImages();
        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }

    // Lấy ProductImage theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductImage> getProductImageById(@PathVariable Integer id) {
        ProductImage productImage = productImageService.getProductImageById(id);
        if (productImage != null) {
            return new ResponseEntity<>(productImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới ProductImage
    @PostMapping
    public ResponseEntity<ProductImage> createProductImage(@RequestBody ProductImage productImage) {
        ProductImage createdProductImage = productImageService.createProductImage(productImage);
        return new ResponseEntity<>(createdProductImage, HttpStatus.CREATED);
    }

    // Cập nhật ProductImage
    @PutMapping("/{id}")
    public ResponseEntity<ProductImage> updateProductImage(@PathVariable Integer id, @RequestBody ProductImage productImage) {
        ProductImage updatedProductImage = productImageService.updateProductImage(id, productImage);
        if (updatedProductImage != null) {
            return new ResponseEntity<>(updatedProductImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa ProductImage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Integer id) {
        if (productImageService.deleteProductImage(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
