package com.blindbox.service.impl;

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
    public Product createProduct(@NonNull ProductCreateRequest request) {
        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        List<ProductImage> images = new ArrayList<>();
        if (request.getProductImages() != null) {
            for (ProductImageCreateRequest imgReq : request.getProductImages()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(imgReq.getImageUrl());
                image.setProduct(product);
                productImageRepository.save(image);
                images.add(image);
            }
        }

        product.setProductImages(images);

        return productRepository.save(product);
    }

    @Override
    @NonNull
    public Product updateProduct(@NonNull Integer id, @NonNull ProductUpdateRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getProductName() != null) existingProduct.setProductName(request.getProductName());
        if (request.getPrice() != null) existingProduct.setPrice(request.getPrice());
        if (request.getDescription() != null) existingProduct.setDescription(request.getDescription());
        if (request.getStock() != null) existingProduct.setStock(request.getStock());

        if (request.getCategoryID() != null) {
            Category category = categoryRepository.findById(request.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        if (request.getProductImages() != null) {
            List<ProductImage> updateImages = new ArrayList<>();
            for (ProductImageUpdateRequest imgReq : request.getProductImages()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(imgReq.getImageUrl());
                image.setProduct(existingProduct);

                productImageRepository.save(image);
                updateImages.add(image);
            }
            existingProduct.setProductImages(updateImages);
        }

        return productRepository.save(existingProduct);
    }

    @Override
    @NonNull
    public void deleteProduct(@NonNull Integer id) {
        productRepository.deleteById(id);
        productImageRepository.deleteAllByProduct_ProductID(id);
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

    /* Product Image */

    @Override
    @NonNull
    public ProductImage updateImage(@NonNull Integer productID, @NonNull Integer imageID, @NonNull ProductImageUpdateRequest request) {
        ProductImage image = productImageRepository.findByProduct_ProductIDAndProductImageID(productID, imageID)
                .orElseThrow(() -> new RuntimeException("Product Image not found"));

        if (request.getProductID() != null) {
            Product product = productRepository.findById(request.getProductID())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            image.setProduct(product);
        }

        if (request.getImageUrl() != null) image.setImageUrl(request.getImageUrl());

        return productImageRepository.save(image);
    }

    @Override
    public void deleteImage(@NonNull Integer productID, @NonNull Integer imageID) {
        productImageRepository.deleteByProduct_ProductIDAndProductImageID(productID, imageID);
    }
}