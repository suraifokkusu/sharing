package com.example.sharing.controller;

import com.example.sharing.dto.ReviewDto;
import com.example.sharing.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.createReview(reviewDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PutMapping
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.updateReview(reviewDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
}
