package com.blindbox.repository;

import com.blindbox.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
