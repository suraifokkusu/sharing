package com.example.sharing.service;

import com.example.sharing.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto getReviewById(Long id);
    ReviewDto updateReview(ReviewDto reviewDto);
    void deleteReview(Long id);
    List<ReviewDto> getAllReviews();
}
