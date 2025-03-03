package com.blindbox.repository;

import com.blindbox.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
