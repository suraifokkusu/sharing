package com.thewhite.blank.action.review.update;

import com.thewhite.blank.model.Exercise;
import com.thewhite.blank.model.Review;
import com.thewhite.blank.model.User;
import com.thewhite.blank.service.exercise.ExerciseService;
import com.thewhite.blank.service.review.ReviewService;
import com.thewhite.blank.service.review.argument.UpdateReviewArgument;
import com.thewhite.blank.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Generated by Thanos
 */
@Component
@RequiredArgsConstructor
public class UpdateReviewAction {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ExerciseService exerciseService;

    @Transactional
    public Review execute(@NonNull UpdateReviewActionArgument argument) {
        User user = userService.getExisting(argument.getUserId());
        Exercise exercise = exerciseService.getExisting(argument.getExerciseId());
        return reviewService.update(argument.getId(),
                                    UpdateReviewArgument.builder()
                                                        .user(user)
                                                        .exercise(exercise)
                                                        .description(argument.getDescription())
                                                        .build());
    }
}