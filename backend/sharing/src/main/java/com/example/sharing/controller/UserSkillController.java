package com.example.sharing.controller;

import com.example.sharing.dto.UserSkillDto;
import com.example.sharing.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-skills")
@CrossOrigin(origins = "http://localhost:5173")
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;

    @PostMapping
    public ResponseEntity<UserSkillDto> createUserSkill(@RequestBody UserSkillDto userSkillDto) {
        return ResponseEntity.ok(userSkillService.createUserSkill(userSkillDto));
    }

    @GetMapping("/{userId}/{skillId}")
    public ResponseEntity<UserSkillDto> getUserSkillById(@PathVariable Long userId, @PathVariable Long skillId) {
        return ResponseEntity.ok(userSkillService.getUserSkillById(userId, skillId));
    }

    @DeleteMapping("/{userId}/{skillId}")
    public ResponseEntity<Void> deleteUserSkill(@PathVariable Long userId, @PathVariable Long skillId) {
        userSkillService.deleteUserSkill(userId, skillId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserSkillDto>> getAllUserSkills() {
        return ResponseEntity.ok(userSkillService.getAllUserSkills());
    }
}