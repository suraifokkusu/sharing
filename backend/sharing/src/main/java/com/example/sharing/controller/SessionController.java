package com.example.sharing.controller;

import com.example.sharing.dto.SessionDto;
import com.example.sharing.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionDto sessionDto) {
        return ResponseEntity.ok(sessionService.createSession(sessionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }

    @PutMapping
    public ResponseEntity<SessionDto> updateSession(@RequestBody SessionDto sessionDto) {
        return ResponseEntity.ok(sessionService.updateSession(sessionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SessionDto>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
}
