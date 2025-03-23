package com.blindbox.repository;

import com.blindbox.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
