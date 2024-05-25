package com.example.sharing.serviceImpl;

import com.example.sharing.dto.UserSkillDto;
import com.example.sharing.entity.UserSkill;
import com.example.sharing.entity.UserSkillId;
import com.example.sharing.mapper.UserSkillMapper;
import com.example.sharing.repository.UserSkillRepository;
import com.example.sharing.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    private static final Logger logger = LoggerFactory.getLogger(UserSkillServiceImpl.class);

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private UserSkillMapper userSkillMapper;

    @Override
    public UserSkillDto createUserSkill(UserSkillDto userSkillDto) {
        logger.info("Creating user skill with data: {}", userSkillDto);
        UserSkill userSkill = userSkillMapper.toEntity(userSkillDto);
        UserSkill savedUserSkill = userSkillRepository.save(userSkill);
        logger.info("User skill created with userId: {}, skillId: {}", savedUserSkill.getUser().getUserId(), savedUserSkill.getSkill().getSkillId());
        return userSkillMapper.toDto(savedUserSkill);
    }

    @Override
    public UserSkillDto getUserSkillById(Long userId, Long skillId) {
        logger.info("Fetching user skill with userId: {} and skillId: {}", userId, skillId);
        UserSkillId userSkillId = new UserSkillId(userId, skillId);
        UserSkill userSkill = userSkillRepository.findById(userSkillId)
                .orElseThrow(() -> new RuntimeException("User skill not found"));
        logger.info("Fetched user skill: {}", userSkill);
        return userSkillMapper.toDto(userSkill);
    }

    @Override
    @Transactional
    public void deleteUserSkill(Long userId, Long skillId) {
        try {
            logger.info("Attempting to delete user skill with userId: {} and skillId: {}", userId, skillId);
            UserSkillId userSkillId = new UserSkillId(userId, skillId);
            userSkillRepository.deleteById(userSkillId);
            logger.info("Successfully deleted user skill with userId: {} and skillId: {}", userId, skillId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting user skill with userId: {} and skillId: {}", userId, skillId, e);
            throw new RuntimeException("Failed to delete user skill", e);
        }
    }

    @Override
    public List<UserSkillDto> getAllUserSkills() {
        logger.info("Fetching all user skills");
        List<UserSkill> userSkills = userSkillRepository.findAll();
        List<UserSkillDto> userSkillDtos = userSkills.stream()
                .map(userSkillMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} user skills", userSkillDtos.size());
        return userSkillDtos;
    }


}