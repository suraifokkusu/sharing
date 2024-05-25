package com.example.sharing.mapper;

import com.example.sharing.dto.AdSkillDto;
import com.example.sharing.entity.AdSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AdvertisementMapper.class, SkillMapper.class})
public interface AdSkillMapper {

    @Mappings({
            @Mapping(target = "adId", source = "ad.adId"),
            @Mapping(target = "skillId", source = "skill.skillId")
    })
    AdSkillDto toDto(AdSkill adSkill);

    @Mappings({
            @Mapping(target = "ad.adId", source = "adId"),
            @Mapping(target = "skill.skillId", source = "skillId")
    })
    AdSkill toEntity(AdSkillDto adSkillDto);
}