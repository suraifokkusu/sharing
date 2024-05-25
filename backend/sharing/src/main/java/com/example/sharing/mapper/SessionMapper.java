package com.example.sharing.mapper;

import com.example.sharing.dto.SessionDto;
import com.example.sharing.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AdvertisementMapper.class})
public interface SessionMapper {

    @Mappings({
            @Mapping(target = "sessionId", source = "sessionId"),
            @Mapping(target = "adId", source = "advertisement.adId"),
            @Mapping(target = "sessionDate", source = "sessionDate"),
            @Mapping(target = "platform", source = "platform")
    })
    SessionDto toDto(Session session);

    @Mappings({
            @Mapping(target = "sessionId", source = "sessionId"),
            @Mapping(target = "advertisement.adId", source = "adId"),
            @Mapping(target = "sessionDate", source = "sessionDate"),
            @Mapping(target = "platform", source = "platform")
    })
    Session toEntity(SessionDto sessionDto);

    @Mappings({
            @Mapping(target = "sessionDate", source = "sessionDate"),
            @Mapping(target = "platform", source = "platform")
    })
    void updateEntity(@MappingTarget Session session, SessionDto sessionDto);
}
