package com.example.sharing.mapper;

import com.example.sharing.dto.UserSkillDto;
import com.example.sharing.entity.UserSkill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserSkillMapper {
    UserSkillDto toDto(UserSkill userSkill);
    UserSkill toEntity(UserSkillDto userSkillDto);

    void updateEntity(@MappingTarget UserSkill userSkill, UserSkillDto userSkillDto);
}