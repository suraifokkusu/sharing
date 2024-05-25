package com.example.sharing.controller;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.service.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdvertisementControllerIT {

    @Mock
    private AdvertisementService advertisementService;

    @InjectMocks
    private AdvertisementController advertisementController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(advertisementController).build();
    }

    @Test
    public void testCreateAdvertisement() throws Exception {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setAdId(1L);
        advertisementDto.setUserId(1L);
        advertisementDto.setCategory("Programming");
        advertisementDto.setDescription("Learn Java from scratch");
        advertisementDto.setAdType("offer");
        advertisementDto.setStatus("active");

        when(advertisementService.createAdvertisement(any(AdvertisementDto.class))).thenReturn(advertisementDto);

        mockMvc.perform(post("/api/advertisements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"category\":\"Programming\",\"description\":\"Learn Java from scratch\",\"adType\":\"offer\",\"status\":\"active\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adId").value(1));
    }

    @Test
    public void testGetAllAdvertisements() throws Exception {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setAdId(1L);
        advertisementDto.setUserId(1L);
        advertisementDto.setCategory("Programming");
        advertisementDto.setDescription("Learn Java from scratch");
        advertisementDto.setAdType("offer");
        advertisementDto.setStatus("active");

        when(advertisementService.getAllAdvertisements()).thenReturn(Arrays.asList(advertisementDto));

        mockMvc.perform(get("/api/advertisements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].adId").value(1));
    }
}
