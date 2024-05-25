package com.thewhite.blank.api.exercise.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Generated by Thanos
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseDto {

    String title;
    String description;
    UUID announcementId;
    UUID teacherId;
    UUID studentId;
    LocalDateTime date;
}