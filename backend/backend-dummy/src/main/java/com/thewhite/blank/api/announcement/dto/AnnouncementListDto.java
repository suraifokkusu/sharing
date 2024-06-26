package com.thewhite.blank.api.announcement.dto;

import com.thewhite.blank.model.AnnouncementStatus;
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
public class AnnouncementListDto {

    UUID id;

    String title;

    UUID imageId;

    Long price;

    String description;

    User teacher;

    AnnouncementStatus status;

}