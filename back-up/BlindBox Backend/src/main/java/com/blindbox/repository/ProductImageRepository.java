package com.blindbox.repository;

import com.blindbox.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    Optional<ProductImage> findByProduct_ProductIDAndProductImageID(Integer productID, Integer imageID);

    void deleteAllByProduct_ProductID(Integer id);
    void deleteByProduct_ProductIDAndProductImageID(Integer productID, Integer imageID);

    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
