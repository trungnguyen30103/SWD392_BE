package com.blindbox.service;

import com.blindbox.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByUserId(Long userId);
    List<Review> getReviewsByBlinkboxId(Long blinkboxId);
    List<Review> getReviewsByProductId(Long productId);
    Review getReviewById(Long id);
    Review createReview(Review review);
    void deleteReview(Long id);
}
