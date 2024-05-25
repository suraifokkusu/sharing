package com.example.sharing.mapper;

import com.example.sharing.dto.SkillDto;
import com.example.sharing.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    @Mappings({
            @Mapping(target = "skillId", source = "skill.skillId"),
            @Mapping(target = "skillName", source = "skill.skillName")
    })
    SkillDto toDto(Skill skill);

    @Mappings({
            @Mapping(target = "skillId", source = "skillDto.skillId"),
            @Mapping(target = "skillName", source = "skillDto.skillName")
    })
    Skill toEntity(SkillDto skillDto);

    @Mappings({
            @Mapping(target = "skillName", source = "skillDto.skillName")
    })
    void updateEntity(@MappingTarget Skill skill, SkillDto skillDto);
}
