package com.example.sharing.serviceImpl;

import com.example.sharing.dto.ReviewDto;
import com.example.sharing.entity.Review;
import com.example.sharing.mapper.ReviewMapper;
import com.example.sharing.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    public void testCreateReview() {
        // Given
        ReviewDto dto = new ReviewDto();
        dto.setRating(5);
        Review review = new Review();
        when(reviewMapper.toEntity(dto)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        when(reviewMapper.toDto(review)).thenReturn(dto);

        // When
        ReviewDto result = reviewService.createReview(dto);

        // Then
        assertNotNull(result);
        assertEquals(5, result.getRating());
        verify(reviewRepository).save(review);
        verify(reviewMapper).toDto(review);
    }
}
