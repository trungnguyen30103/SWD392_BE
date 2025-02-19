package com.blinkbox.repository;

import com.blinkbox.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
    List<Review> findByBlinkboxId(Long blinkboxId);
    List<Review> findByProductId(Long productId);
}
