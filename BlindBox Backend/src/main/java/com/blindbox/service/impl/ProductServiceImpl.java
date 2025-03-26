package com.blindbox.service.impl;

import com.blindbox.enums.Blindbox.ProductStatus;
import com.blindbox.model.Product;
import com.blindbox.model.ProductImage;
import com.blindbox.model.Category;
import com.blindbox.repository.ProductImageRepository;
import com.blindbox.repository.ProductRepository;
import com.blindbox.repository.CategoryRepository;
import com.blindbox.request.Create.Product.ProductCreateRequest;
import com.blindbox.request.Create.Product.ProductImageCreateRequest;
import com.blindbox.request.Update.Product.ProductImageUpdateRequest;
import com.blindbox.request.Update.Product.ProductUpdateRequest;
import com.blindbox.service.ProductService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
    }

    /* Product */

    @Override
    @NonNull
    @Transactional
    public Product createProduct(@NonNull ProductCreateRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock() != 0 ? request.getStock() : 0);
        product.setStatus(request.getStock() == 0 ? ProductStatus.OUT_OF_STOCK : ProductStatus.ACTIVE);

        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        product = productRepository.save(product);

        // Set image
        List<ProductImage> images = new ArrayList<>();
        if (request.getProductImages() != null) {
            for (ProductImageCreateRequest imgReq : request.getProductImages()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(imgReq.getImageUrl());
                image.setProduct(product);
                images.add(image);
            }
            product.setProductImages(images);
        }

        productRepository.save(product);
        return product;
    }

    @Override
    @NonNull
    @Transactional
    public Product updateProduct(@NonNull Integer productID, @NonNull ProductUpdateRequest request) {
        Product existingProduct = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getProductName() != null) existingProduct.setProductName(request.getProductName());
        if (request.getPrice() != null) existingProduct.setPrice(request.getPrice());
        if (request.getDescription() != null) existingProduct.setDescription(request.getDescription());
        if (request.getStock() != null) {
            existingProduct.setStock(request.getStock());
            existingProduct.setStatus(request.getStock() == 0 ? ProductStatus.OUT_OF_STOCK : ProductStatus.ACTIVE);
        }

        if (request.getCategoryID() != null) {
            Category category = categoryRepository.findById(request.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        // Update image(s)
        if (request.getProductImageUpdateRequests() != null) {
            for (ProductImageUpdateRequest imageUpdateRequest : request.getProductImageUpdateRequests()) {
                ProductImage image = productImageRepository.findByProduct_ProductIDAndProductImageID(productID, imageUpdateRequest.getProductImageID())
                                .orElseThrow(() -> new RuntimeException("Product Image not found"));
                if (imageUpdateRequest.getImageUrl() != null) image.setImageUrl(imageUpdateRequest.getImageUrl());
                if (imageUpdateRequest.getAltText() != null) image.setAltText(imageUpdateRequest.getAltText());
            }
        }

        // Add new image(s)
        if (request.getProductImageCreateRequests() != null) {
            for (ProductImageCreateRequest imageCreateRequest : request.getProductImageCreateRequests()) {
                ProductImage newImage = new ProductImage();
                newImage.setImageUrl(imageCreateRequest.getImageUrl());
                newImage.setProduct(existingProduct);
                newImage.setAltText(imageCreateRequest.getAltText());

                // Save image
                ProductImage saveImage = productImageRepository.save(newImage);
                existingProduct.getProductImages().add(saveImage);
            }
        }

        // Save
        return productRepository.save(existingProduct);
    }

    @Override
    @NonNull
    public void deleteProduct(@NonNull Integer productID) {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update status to DISABLE
        product.setStatus(ProductStatus.DISABLE);

        // Save change
        productRepository.save(product);
    }

    @Override
    @NonNull
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @NonNull
    public Product getProductById(@NonNull Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> getProductByCategory(Integer categoryID) {
        return productRepository.findByCategory_CategoryID(categoryID);
    }

    @Override
    public List<Product> getActiveProduct() {
        return productRepository.findByStatus(ProductStatus.ACTIVE);
    }

    @Override
    public List<Product> getDisableProduct() {
        return productRepository.findByStatus(ProductStatus.DISABLE);
    }

    @Override
    public List<Product> getOutOfStockProduct() {
        return productRepository.findByStatus(ProductStatus.OUT_OF_STOCK);
    }

    @Override
    public List<Product> getActiveProductByCategory(Integer categoryID) {
        return productRepository.findByStatusAndCategory_CategoryID(ProductStatus.ACTIVE, categoryID);
    }

    /* Product Image */

    @Override
    public void deleteImage(@NonNull Integer productID, @NonNull Integer imageID) {
        productImageRepository.deleteByProduct_ProductIDAndProductImageID(productID, imageID);
    }
}