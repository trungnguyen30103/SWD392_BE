package com.blindbox.service;

import com.blindbox.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review getReviewById(Integer id);
    Review createReview(Review review);
    Review updateReview(Integer id, Review review);
    boolean deleteReview(Integer id);
}
