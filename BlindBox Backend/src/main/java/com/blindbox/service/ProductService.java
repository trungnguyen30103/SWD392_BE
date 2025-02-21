package com.blindbox.service;

import com.blindbox.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product createProduct(Product product);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
}
