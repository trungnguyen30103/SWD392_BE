package com.blindbox.controller;

import com.blindbox.model.Product;
import com.blindbox.request.Create.Product.ProductCreateRequest;
import com.blindbox.request.Update.Product.ProductUpdateRequest;
import com.blindbox.response.ResponseData;
import com.blindbox.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Management System", description = "Operations pertaining to products in the Product Management System")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* Product */

    @Operation(summary = "Create a new product", description = "Add a new product to the catalog")
    @PostMapping
    public ResponseEntity<ResponseData> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        try {
            Product createdProduct = productService.createProduct(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Product created successfully", createdProduct, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to create product" + e.getMessage(), null, null));
        }
    }

    @Operation(summary = "Update an existing product", description = "Update an existing product using its ID")
    @PutMapping("/{productID}")
    public ResponseEntity<ResponseData> updateProduct(@PathVariable Integer productID, @RequestBody ProductUpdateRequest request) {
        try {
            Product updatedProduct = productService.updateProduct(productID, request);
            return ResponseEntity.ok(new ResponseData(200, true, "Product updated successfully", updatedProduct, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update product", null, null));
        }
    }

    @Operation(summary = "Disable a product by ID")
    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productID) {
        productService.deleteProduct(productID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all products", description = "Retrieve a list of all available products")
    @GetMapping
    public ResponseEntity<ResponseData> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ResponseData(200, true, "Products retrieved successfully", products, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve products", null, null));
        }
    }

    @Operation(summary = "Get a product by ID", description = "Retrieve a single product using its ID")
    @GetMapping("/{productID}")
    public ResponseEntity<ResponseData> getProductById(@PathVariable Integer productID) {
        try {
            Product product = productService.getProductById(productID);
            return ResponseEntity.ok(new ResponseData(200, true, "Product retrieved successfully", product, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Product not found", null, null));
        }
    }

    @Operation(summary = "Get products by name", description = "Search for products using name")
    @GetMapping("/search")
    public ResponseEntity<ResponseData> searchProductsByName(@RequestParam String name) {
        try {
            List<Product> products = productService.getProductByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "There is no product with name '" + name + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Product(s) found", products, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search products", null, null));
        }
    }

    @Operation(summary = "Get products by category", description = "Retrieve products by category ID")
    @GetMapping("/category/{categoryID}")
    public ResponseEntity<ResponseData> getProductsByCategory(@PathVariable Integer categoryID) {
        try {
            List<Product> products = productService.getProductByCategory(categoryID);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No products found for category ID: " + categoryID, null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Products retrieved successfully", products, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve products", null, null));
        }
    }

    @Operation(summary = "Get ACTIVE products", description = "Retrieve a list of all ACTIVE products")
    @GetMapping("/status/active")
    public ResponseEntity<ResponseData> getActiveProducts() {
        List<Product> products = productService.getActiveProduct();
        return ResponseEntity.ok(new ResponseData(200, true, "Active products retrieved successfully", products, null));
    }

    @Operation(summary = "Get DISABLE products", description = "Retrieve a list of all DISABLE products")
    @GetMapping("/status/disable")
    public ResponseEntity<ResponseData> getDisableProducts() {
        List<Product> products = productService.getDisableProduct();
        return ResponseEntity.ok(new ResponseData(200, true, "Disabled products retrieved successfully", products, null));
    }

    @Operation(summary = "Get OUT_OF_STOCK products", description = "Retrieve a list of all OUT_OF_STOCK products")
    @GetMapping("/status/out-of-stock")
    public ResponseEntity<ResponseData> getOutOfStockProducts() {
        List<Product> products = productService.getOutOfStockProduct();
        return ResponseEntity.ok(new ResponseData(200, true, "Out of stock products retrieved successfully", products, null));
    }

    @Operation(summary = "Get ACTIVE products by category", description = "Retrieve a list of all ACTIVE products by category ID")
    @GetMapping("/status/active/category/{categoryID}")
    public ResponseEntity<ResponseData> getActiveProductsByCategory(@PathVariable Integer categoryID) {
        try {
            List<Product> products = productService.getActiveProductByCategory(categoryID);
            return ResponseEntity.ok(new ResponseData(200, true, "Active products by category retrieved successfully", products, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve active products by category", null, null));
        }
    }

    /* Product Image */

    @Operation(summary = "Delete an image by productID and imageID")
    @DeleteMapping("/{productID}/product-images/{imageID}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer productID, @PathVariable Integer imageID) {
        productService.deleteImage(productID, imageID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}