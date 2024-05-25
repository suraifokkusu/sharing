package com.thewhite.blank.api.review.dto;

import com.thewhite.blank.api.exercise.dto.ExerciseDto;
import com.thewhite.blank.api.user.dto.UserDto;
import com.thewhite.blank.model.Exercise;
import com.thewhite.blank.model.User;
import lombok.*;

import java.util.UUID;


/**
 * Generated by Thanos
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    UUID id;

    UserDto user;

    ExerciseDto exercise;

    String description;

}