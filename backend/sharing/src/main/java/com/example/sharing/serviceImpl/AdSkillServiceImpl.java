package com.example.sharing.serviceImpl;

import com.example.sharing.dto.AdSkillDto;
import com.example.sharing.entity.AdSkill;
import com.example.sharing.entity.AdSkillId;
import com.example.sharing.mapper.AdSkillMapper;
import com.example.sharing.repository.AdSkillRepository;
import com.example.sharing.service.AdSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdSkillServiceImpl implements AdSkillService {

    private static final Logger logger = LoggerFactory.getLogger(AdSkillServiceImpl.class);

    @Autowired
    private AdSkillRepository adSkillRepository;

    @Autowired
    private AdSkillMapper adSkillMapper;

    @Override
    public AdSkillDto createAdSkill(AdSkillDto adSkillDto) {
        logger.info("Creating ad skill with data: {}", adSkillDto);
        AdSkill adSkill = adSkillMapper.toEntity(adSkillDto);
        AdSkill savedAdSkill = adSkillRepository.save(adSkill);
        logger.info("Ad skill created with adId: {}, skillId: {}", savedAdSkill.getAd().getAdId(), savedAdSkill.getSkill().getSkillId());
        return adSkillMapper.toDto(savedAdSkill);
    }

    @Override
    public AdSkillDto getAdSkillById(Long adId, Long skillId) {
        logger.info("Fetching ad skill with adId: {} and skillId: {}", adId, skillId);
        AdSkillId adSkillId = new AdSkillId(adId, skillId);
        AdSkill adSkill = adSkillRepository.findById(adSkillId)
                .orElseThrow(() -> new RuntimeException("Ad skill not found"));
        logger.info("Fetched ad skill: {}", adSkill);
        return adSkillMapper.toDto(adSkill);
    }

    @Override
    @Transactional
    public void deleteAdSkill(Long adId, Long skillId) {
        try {
            logger.info("Attempting to delete ad skill with adId: {} and skillId: {}", adId, skillId);
            AdSkillId adSkillId = new AdSkillId(adId, skillId);
            adSkillRepository.deleteById(adSkillId);
            logger.info("Successfully deleted ad skill with adId: {} and skillId: {}", adId, skillId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting ad skill with adId: {} and skillId: {}", adId, skillId, e);
            throw new RuntimeException("Failed to delete ad skill", e);
        }
    }

    @Override
    public List<AdSkillDto> getAllAdSkills() {
        logger.info("Fetching all ad skills");
        List<AdSkill> adSkills = adSkillRepository.findAll();
        List<AdSkillDto> adSkillDtos = adSkills.stream()
                .map(adSkillMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} ad skills", adSkillDtos.size());
        return adSkillDtos;
    }
}