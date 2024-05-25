package com.example.sharing.serviceImpl;

import com.example.sharing.dto.ReviewDto;
import com.example.sharing.entity.Review;
import com.example.sharing.mapper.ReviewMapper;
import com.example.sharing.repository.ReviewRepository;
import com.example.sharing.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        logger.info("Creating review with data: {}", reviewDto);
        Review review = reviewMapper.toEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        logger.info("Review created with id: {}", savedReview.getReviewId());
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        logger.info("Fetching review with id: {}", id);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        logger.info("Fetched review: {}", review);
        return reviewMapper.toDto(review);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        logger.info("Updating review with data: {}", reviewDto);
        Review existingReview = reviewRepository.findById(reviewDto.getReviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewMapper.updateEntity(existingReview, reviewDto);
        Review updatedReview = reviewRepository.save(existingReview);
        logger.info("Review updated: {}", updatedReview);
        return reviewMapper.toDto(updatedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        try {
            logger.info("Attempting to delete review with id: {}", id);
            reviewRepository.deleteById(id);
            logger.info("Successfully deleted review with id: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting review with id: {}", id, e);
            throw new RuntimeException("Failed to delete review", e);
        }
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        logger.info("Fetching all reviews");
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} reviews", reviewDtos.size());
        return reviewDtos;
    }
}
