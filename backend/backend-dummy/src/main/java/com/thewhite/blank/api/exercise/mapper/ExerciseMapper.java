package com.thewhite.blank.api.exercise.mapper;

import com.thewhite.blank.action.exercise.create.CreateExerciseActionArgument;
import com.thewhite.blank.action.exercise.update.UpdateExerciseActionArgument;
import com.thewhite.blank.api.exercise.dto.*;
import com.thewhite.blank.model.Exercise;
import com.thewhite.blank.service.exercise.argument.SearchExerciseArgument;
import org.mapstruct.Mapper;

import java.util.UUID;


/**
 * Generated by Thanos
 */
@Mapper
public interface ExerciseMapper {

    ExerciseDto toDto(Exercise entity);

    ExerciseListDto toListDto(Exercise entity);

    CreateExerciseActionArgument toCreateActionArgument(CreateExerciseDto dto);

    UpdateExerciseActionArgument toUpdateActionArgument(UUID id, UpdateExerciseDto dto);

    SearchExerciseArgument toSearchArgument(SearchExerciseDto dto);
}