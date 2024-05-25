package com.example.sharing.controller;

import com.example.sharing.dto.AdvertisementDto;
import com.example.sharing.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
//@CrossOrigin(origins = "http://localhost:5174")
//@CrossOrigin(origins = "*")

public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        return ResponseEntity.ok(advertisementService.createAdvertisement(advertisementDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.getAdvertisementById(id));
    }

    @PutMapping
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        return ResponseEntity.ok(advertisementService.updateAdvertisement(advertisementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAllAdvertisements());
    }
}