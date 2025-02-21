package com.blindbox.controller;

import com.blindbox.model.Review;
import com.blindbox.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/user/{userID}")
    public List<Review> getReviewsByUserId(@PathVariable Integer userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/blinkbox/{blindboxID}")
    public List<Review> getReviewsByBlinkboxId(@PathVariable Integer blinkboxId) {
        return reviewService.getReviewsByBlinkboxId(blinkboxId);
    }

    @GetMapping("/product/{productID}")
    public List<Review> getReviewsByProductId(@PathVariable Integer productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
