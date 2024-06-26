package com.thewhite.blank.service.announcement.argument;

import com.thewhite.blank.model.User;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;


/**
 * Generated by Thanos
 */
@Value
@Builder
public class UpdateAnnouncementArgument {

    String title;

    UUID imageId;

    Long price;

    String description;

    User teacher;

}
