package com.thewhite.blank.action.exercise.create;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Generated by Thanos
 */
@Value
@Builder
public class CreateExerciseActionArgument {

    String title;
    String description;
    UUID announcementId;
    UUID teacherId;
    UUID studentId;
    LocalDateTime date;
}