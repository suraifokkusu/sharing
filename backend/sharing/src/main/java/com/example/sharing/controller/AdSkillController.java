package com.example.sharing.controller;

import com.example.sharing.dto.AdSkillDto;
import com.example.sharing.service.AdSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ad-skills")
@CrossOrigin(origins = "http://localhost:5173")
public class AdSkillController {

    @Autowired
    private AdSkillService adSkillService;

    @PostMapping
    public ResponseEntity<AdSkillDto> createAdSkill(@RequestBody AdSkillDto adSkillDto) {
        return ResponseEntity.ok(adSkillService.createAdSkill(adSkillDto));
    }

    @GetMapping("/{adId}/{skillId}")
    public ResponseEntity<AdSkillDto> getAdSkillById(@PathVariable Long adId, @PathVariable Long skillId) {
        return ResponseEntity.ok(adSkillService.getAdSkillById(adId, skillId));
    }

    @DeleteMapping("/{adId}/{skillId}")
    public ResponseEntity<Void> deleteAdSkill(@PathVariable Long adId, @PathVariable Long skillId) {
        adSkillService.deleteAdSkill(adId, skillId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AdSkillDto>> getAllAdSkills() {
        return ResponseEntity.ok(adSkillService.getAllAdSkills());
    }
}