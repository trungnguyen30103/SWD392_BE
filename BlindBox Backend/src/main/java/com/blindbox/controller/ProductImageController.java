package com.blindbox.controller;

import com.blindbox.model.ProductImage;
import com.blindbox.service.ProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Image Management System", description = "Operations pertaining to product images in the Product Image Management System")
@RestController
@RequestMapping("/product-images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    // Lấy tất cả ProductImage
    @Operation(summary = "Get all product images", description = "Retrieve a list of all available product images")
    @GetMapping
    public ResponseEntity<List<ProductImage>> getAllProductImages() {
        List<ProductImage> productImages = productImageService.getAllProductImages();
        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }

    // Lấy ProductImage theo ID
    @Operation(summary = "Get a product image by ID", description = "Retrieve a single product image using its ID")
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
    @Operation(summary = "Create a new product image", description = "Add a new product image to the catalog")
    @PostMapping
    public ResponseEntity<ProductImage> createProductImage(@RequestBody ProductImage productImage) {
        ProductImage createdProductImage = productImageService.createProductImage(productImage);
        return new ResponseEntity<>(createdProductImage, HttpStatus.CREATED);
    }

    // Cập nhật ProductImage
    @Operation(summary = "Update an existing product image", description = "Update an existing product image using its ID")
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
    @Operation(summary = "Delete a product image by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Integer id) {
        if (productImageService.deleteProductImage(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
