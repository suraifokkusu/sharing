package com.example.sharing.mapper;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.entity.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SessionMapper.class, AdSkillMapper.class})
public interface AdvertisementMapper {

    @Mappings({
            @Mapping(target = "adId", source = "adId"),
            @Mapping(target = "userId", source = "user.userId"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "adType", source = "adType"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "sessions", source = "sessions"),
            @Mapping(target = "adSkills", source = "adSkills")
    })
    AdvertisementDto toDto(Advertisement advertisement);

    @Mappings({
            @Mapping(target = "adId", source = "adId"),
            @Mapping(target = "user.userId", source = "userId"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "adType", source = "adType"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "sessions", source = "sessions"),
            @Mapping(target = "adSkills", source = "adSkills")
    })
    Advertisement toEntity(AdvertisementDto advertisementDto);

    @Mappings({
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "adType", source = "adType"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "sessions", source = "sessions"),
            @Mapping(target = "adSkills", source = "adSkills")
    })
    void updateEntity(@MappingTarget Advertisement advertisement, AdvertisementDto advertisementDto);
}