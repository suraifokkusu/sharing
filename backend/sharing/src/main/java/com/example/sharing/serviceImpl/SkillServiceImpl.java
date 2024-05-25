package com.example.sharing.serviceImpl;

import com.example.sharing.dto.SkillDto;
import com.example.sharing.entity.Skill;
import com.example.sharing.entity.UserSkill;
import com.example.sharing.mapper.SkillMapper;
import com.example.sharing.repository.SkillRepository;
import com.example.sharing.repository.UserSkillRepository;
import com.example.sharing.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private static final Logger logger = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public SkillDto createSkill(SkillDto skillDto) {
        logger.info("Creating skill with data: {}", skillDto);
        Skill skill = skillMapper.toEntity(skillDto);
        Skill savedSkill = skillRepository.save(skill);
        logger.info("Skill created with id: {}", savedSkill.getSkillId());
        return skillMapper.toDto(savedSkill);
    }

    @Override
    public SkillDto getSkillById(Long id) {
        logger.info("Fetching skill with id: {}", id);
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        logger.info("Fetched skill: {}", skill);
        return skillMapper.toDto(skill);
    }

    @Override
    public SkillDto updateSkill(SkillDto skillDto) {
        logger.info("Updating skill with data: {}", skillDto);
        Skill existingSkill = skillRepository.findById(skillDto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        skillMapper.updateEntity(existingSkill, skillDto);
        Skill updatedSkill = skillRepository.save(existingSkill);
        logger.info("Skill updated: {}", updatedSkill);
        return skillMapper.toDto(updatedSkill);
    }

    @Override
    @Transactional
    public void deleteSkill(Long id) {
        try {
            logger.info("Attempting to delete skill with id: {}", id);
            // Удаление навыков пользователя перед удалением самого навыка
            List<UserSkill> userSkills = userSkillRepository.findBySkill_SkillId(id);
            userSkillRepository.deleteAll(userSkills);

            skillRepository.deleteById(id);
            logger.info("Successfully deleted skill with id: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting skill with id: {}", id, e);
            throw new RuntimeException("Failed to delete skill", e);
        }
    }

    @Override
    public List<SkillDto> getAllSkills() {
        logger.info("Fetching all skills");
        List<Skill> skills = skillRepository.findAll();
        List<SkillDto> skillDtos = skills.stream()
                .map(skillMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} skills", skillDtos.size());
        return skillDtos;
    }
}
