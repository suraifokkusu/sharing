package com.example.sharing.service;

import com.example.sharing.dto.UserSkillDto;

import java.util.List;

public interface UserSkillService {
    UserSkillDto createUserSkill(UserSkillDto userSkillDto);
    UserSkillDto getUserSkillById(Long userId, Long skillId);
    void deleteUserSkill(Long userId, Long skillId);
    List<UserSkillDto> getAllUserSkills();
}