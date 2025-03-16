package com.blindbox.controller;

import com.blindbox.model.Product;
import com.blindbox.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Product Management System", description = "Operations pertaining to products in the Product Management System")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Get all products", response = List.class)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a product by ID", response = Product.class)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@ApiParam(value = "ID of the product to be fetched", required = true) @PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create a new product", response = Product.class)
    @PostMapping
    public ResponseEntity<Product> createProduct(@ApiParam(value = "Product object to be created", required = true) @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an existing product", response = Product.class)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@ApiParam(value = "ID of the product to be updated", required = true) @PathVariable Integer id,
                                                 @ApiParam(value = "Updated product object", required = true) @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "ID of the product to be deleted", required = true) @PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
