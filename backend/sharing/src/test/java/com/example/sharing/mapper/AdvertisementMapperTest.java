package com.example.sharing.mapper;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.entity.Advertisement;
import com.example.sharing.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementMapperTest {

    private AdvertisementMapper advertisementMapper = Mappers.getMapper(AdvertisementMapper.class);

    @Test
    public void testToDto() {
        User user = new User();
        user.setUserId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setTelegramId("john_doe");
        user.setProfileText("Experienced programmer");
        user.setRating(new BigDecimal("4.5"));
        user.setCurrencyBalance(new BigDecimal("100.00"));

        Advertisement advertisement = new Advertisement();
        advertisement.setAdId(1L);
        advertisement.setUser(user);
        advertisement.setCategory("Programming");
        advertisement.setDescription("Learn Java from scratch");
        advertisement.setAdType("offer");
        advertisement.setStatus("active");

        AdvertisementDto advertisementDto = advertisementMapper.toDto(advertisement);

        assertEquals(advertisement.getAdId(), advertisementDto.getAdId());
        assertEquals(advertisement.getUser().getUserId(), advertisementDto.getUserId());
        assertEquals(advertisement.getCategory(), advertisementDto.getCategory());
        assertEquals(advertisement.getDescription(), advertisementDto.getDescription());
        assertEquals(advertisement.getAdType(), advertisementDto.getAdType());
        assertEquals(advertisement.getStatus(), advertisementDto.getStatus());
    }

    @Test
    public void testToEntity() {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setAdId(1L);
        advertisementDto.setUserId(1L);
        advertisementDto.setCategory("Programming");
        advertisementDto.setDescription("Learn Java from scratch");
        advertisementDto.setAdType("offer");
        advertisementDto.setStatus("active");

        Advertisement advertisement = advertisementMapper.toEntity(advertisementDto);

        assertEquals(advertisementDto.getAdId(), advertisement.getAdId());
        assertEquals(advertisementDto.getUserId(), advertisement.getUser().getUserId());
        assertEquals(advertisementDto.getCategory(), advertisement.getCategory());
        assertEquals(advertisementDto.getDescription(), advertisement.getDescription());
        assertEquals(advertisementDto.getAdType(), advertisement.getAdType());
        assertEquals(advertisementDto.getStatus(), advertisement.getStatus());
    }
}
