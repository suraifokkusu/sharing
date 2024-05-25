package com.example.sharing.service;

import com.example.sharing.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementService {
    AdvertisementDto createAdvertisement(AdvertisementDto advertisementDto);
    AdvertisementDto getAdvertisementById(Long id);
    AdvertisementDto updateAdvertisement(AdvertisementDto advertisementDto);
    void deleteAdvertisement(Long id);
    List<AdvertisementDto> getAllAdvertisements();
}