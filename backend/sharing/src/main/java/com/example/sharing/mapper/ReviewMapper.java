package com.example.sharing.mapper;

import com.example.sharing.dto.ReviewDto;
import com.example.sharing.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);

    void updateEntity(@MappingTarget Review review, ReviewDto reviewDto);
}
