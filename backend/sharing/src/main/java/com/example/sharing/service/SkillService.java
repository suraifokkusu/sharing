package com.example.sharing.service;

import com.example.sharing.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto createSkill(SkillDto skillDto);
    SkillDto getSkillById(Long id);
    SkillDto updateSkill(SkillDto skillDto);
    void deleteSkill(Long id);
    List<SkillDto> getAllSkills();
}
