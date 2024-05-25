package com.thewhite.blank.service.exercise;

import com.thewhite.blank.model.Exercise;
import com.thewhite.blank.service.exercise.argument.CreateExerciseArgument;
import com.thewhite.blank.service.exercise.argument.SearchExerciseArgument;
import com.thewhite.blank.service.exercise.argument.UpdateExerciseArgument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Generated by Thanos
 */
public interface ExerciseService {

    Exercise create(CreateExerciseArgument argument);

    Optional<Exercise> get(UUID id);

    Exercise getExisting(UUID id);

    List<Exercise> list(SearchExerciseArgument argument, Sort sort);

    Page<Exercise> page(SearchExerciseArgument argument, Pageable pageable);

    Exercise update(UUID id, UpdateExerciseArgument argument);

    void delete(UUID id);
}