package com.example.sharing.service;

import com.example.sharing.dto.AdSkillDto;

import java.util.List;

public interface AdSkillService {
    AdSkillDto createAdSkill(AdSkillDto adSkillDto);
    AdSkillDto getAdSkillById(Long adId, Long skillId);
    void deleteAdSkill(Long adId, Long skillId);
    List<AdSkillDto> getAllAdSkills();
}