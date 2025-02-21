package com.blindbox.repository;

import com.blindbox.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByUserId(Integer userId);
    List<Review> findByBlindboxId(Integer blindboxId);
    List<Review> findByProductId(Integer productId);
}
