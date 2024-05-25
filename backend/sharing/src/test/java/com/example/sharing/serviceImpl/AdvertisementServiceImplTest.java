package com.example.sharing.serviceImpl;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.entity.Advertisement;
import com.example.sharing.mapper.AdvertisementMapper;
import com.example.sharing.repository.AdvertisementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdvertisementServiceImplTest {

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private AdvertisementMapper advertisementMapper;

    @InjectMocks
    private AdvertisementServiceImpl advertisementService;

    @Test
    public void testCreateAdvertisement() {
        // Given
        AdvertisementDto dto = new AdvertisementDto();
        dto.setDescription("Test Description");
        Advertisement advertisement = new Advertisement();
        when(advertisementMapper.toEntity(dto)).thenReturn(advertisement);
        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);
        when(advertisementMapper.toDto(advertisement)).thenReturn(dto);

        // When
        AdvertisementDto result = advertisementService.createAdvertisement(dto);

        // Then
        assertNotNull(result);
        assertEquals("Test Description", result.getDescription());
        verify(advertisementRepository).save(advertisement);
        verify(advertisementMapper).toDto(advertisement);
    }
}
