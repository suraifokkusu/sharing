package com.example.sharing.serviceImpl;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.entity.Advertisement;
import com.example.sharing.entity.Session;
import com.example.sharing.entity.Review;
import com.example.sharing.repository.AdvertisementRepository;
import com.example.sharing.repository.ReviewRepository;
import com.example.sharing.repository.SessionRepository;
import com.example.sharing.service.AdvertisementService;
import com.example.sharing.mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public AdvertisementDto createAdvertisement(AdvertisementDto advertisementDto) {
        logger.info("Creating new advertisement");
        Advertisement advertisement = advertisementMapper.toEntity(advertisementDto);
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        logger.info("Advertisement created with id: {}", savedAdvertisement.getAdId());
        return advertisementMapper.toDto(savedAdvertisement);
    }

    @Override
    public AdvertisementDto getAdvertisementById(Long id) {
        logger.info("Fetching advertisement with id: {}", id);
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advertisement not found"));
        logger.info("Advertisement fetched: {}", advertisement);
        return advertisementMapper.toDto(advertisement);
    }

    @Override
    public AdvertisementDto updateAdvertisement(AdvertisementDto advertisementDto) {
        logger.info("Updating advertisement with id: {}", advertisementDto.getAdId());
        Advertisement existingAdvertisement = advertisementRepository.findById(advertisementDto.getAdId())
                .orElseThrow(() -> new RuntimeException("Advertisement not found"));
        advertisementMapper.updateEntity(existingAdvertisement, advertisementDto);
        Advertisement updatedAdvertisement = advertisementRepository.save(existingAdvertisement);
        logger.info("Advertisement updated: {}", updatedAdvertisement);
        return advertisementMapper.toDto(updatedAdvertisement);
    }

    @Override
    @Transactional
    public void deleteAdvertisement(Long id) {
        try {
            logger.info("Attempting to delete advertisement with id: {}", id);

            Advertisement advertisement = advertisementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Advertisement not found"));

            // Удаляем все связанные сессии и их отзывы
            for (Session session : advertisement.getSessions()) {
                for (Review review : session.getReviews()) {
                    reviewRepository.delete(review);
                }
                sessionRepository.delete(session);
            }

            advertisementRepository.delete(advertisement);
            logger.info("Successfully deleted advertisement with id: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting advertisement with id: {}", id, e);
            throw new RuntimeException("Failed to delete advertisement", e);
        }
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {
        logger.info("Fetching all advertisements");
        List<Advertisement> advertisements = advertisementRepository.findAll();
        List<AdvertisementDto> advertisementDtos = advertisements.stream()
                .map(advertisementMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} advertisements", advertisementDtos.size());
        return advertisementDtos;
    }
}