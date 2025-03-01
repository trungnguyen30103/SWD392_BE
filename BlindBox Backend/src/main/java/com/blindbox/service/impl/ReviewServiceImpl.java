package com.blindbox.service.impl;

import com.blindbox.model.Review;
import com.blindbox.repository.ReviewRepository;
import com.blindbox.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> getReviewsByBlinkboxId(Integer blinkboxId) {
        return reviewRepository.findByBlindboxId(blinkboxId);
    }

    @Override
    public List<Review> getReviewsByProductId(Integer productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review getReviewById(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }
}
