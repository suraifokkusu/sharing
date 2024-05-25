package com.example.sharing.controller;

import com.example.sharing.dto.SkillDto;
import com.example.sharing.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        return ResponseEntity.ok(skillService.createSkill(skillDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @PutMapping
    public ResponseEntity<SkillDto> updateSkill(@RequestBody SkillDto skillDto) {
        return ResponseEntity.ok(skillService.updateSkill(skillDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}
