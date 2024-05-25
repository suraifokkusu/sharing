package com.example.sharing.serviceImpl;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.entity.Advertisement;
import com.example.sharing.entity.User;
import com.example.sharing.mapper.AdvertisementMapper;
import com.example.sharing.repository.AdvertisementRepository;
import com.example.sharing.service.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdvertisementServiceTest {

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private AdvertisementMapper advertisementMapper;

    @InjectMocks
    private AdvertisementServiceImpl advertisementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAdvertisement() {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setAdId(1L);
        advertisementDto.setUserId(1L);
        advertisementDto.setCategory("Programming");
        advertisementDto.setDescription("Learn Java from scratch");
        advertisementDto.setAdType("offer");
        advertisementDto.setStatus("active");

        Advertisement advertisement = new Advertisement();
        advertisement.setAdId(1L);
        advertisement.setCategory("Programming");
        advertisement.setDescription("Learn Java from scratch");
        advertisement.setAdType("offer");
        advertisement.setStatus("active");

        when(advertisementMapper.toEntity(any(AdvertisementDto.class))).thenReturn(advertisement);
        when(advertisementRepository.save(any(Advertisement.class))).thenReturn(advertisement);
        when(advertisementMapper.toDto(any(Advertisement.class))).thenReturn(advertisementDto);

        AdvertisementDto result = advertisementService.createAdvertisement(advertisementDto);

        assertEquals(advertisementDto.getAdId(), result.getAdId());
        verify(advertisementRepository, times(1)).save(any(Advertisement.class));
    }

    @Test
    public void testGetAdvertisementById() {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdId(1L);
        advertisement.setCategory("Programming");
        advertisement.setDescription("Learn Java from scratch");
        advertisement.setAdType("offer");
        advertisement.setStatus("active");

        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setAdId(1L);
        advertisementDto.setCategory("Programming");
        advertisementDto.setDescription("Learn Java from scratch");
        advertisementDto.setAdType("offer");
        advertisementDto.setStatus("active");

        when(advertisementRepository.findById(anyLong())).thenReturn(Optional.of(advertisement));
        when(advertisementMapper.toDto(any(Advertisement.class))).thenReturn(advertisementDto);

        AdvertisementDto result = advertisementService.getAdvertisementById(1L);

        assertEquals(advertisementDto.getAdId(), result.getAdId());
        verify(advertisementRepository, times(1)).findById(anyLong());
    }
}
