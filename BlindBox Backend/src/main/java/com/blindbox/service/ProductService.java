package com.blindbox.service;

import com.blindbox.model.Product;
import com.blindbox.request.Create.Product.ProductCreateRequest;
import com.blindbox.request.Update.Product.ProductUpdateRequest;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductService {

    /* Product */
    @NonNull
    Product createProduct(@NonNull ProductCreateRequest request);

    @NonNull
    Product updateProduct(@NonNull Integer id, @NonNull ProductUpdateRequest request);

    void deleteProduct(@NonNull Integer id);

    @NonNull
    List<Product> getAllProducts();

    Product getProductById(@NonNull Integer id);

    List<Product> getProductByName(String name);

    List<Product> getProductByCategory(Integer categoryID);

    List<Product> getActiveProduct();

    List<Product> getDisableProduct();

    List<Product> getOutOfStockProduct();

    List<Product> getActiveProductByCategory(Integer categoryID);

    /* Product Image */

    void deleteImage(@NonNull Integer productID, @NonNull Integer imageID);
}