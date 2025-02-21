package com.blindbox.service;

import com.blindbox.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByUserId(Integer userId);
    List<Review> getReviewsByBlinkboxId(Integer blinkboxId);
    List<Review> getReviewsByProductId(Integer productId);
    Review getReviewById(Integer id);
    Review createReview(Review review);
    void deleteReview(Integer id);
}
