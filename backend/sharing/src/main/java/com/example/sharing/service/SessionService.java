package com.example.sharing.service;

import com.example.sharing.dto.SessionDto;

import java.util.List;

public interface SessionService {
    SessionDto createSession(SessionDto sessionDto);
    List<SessionDto> getAllSessions();
    SessionDto getSessionById(Long id);
    SessionDto updateSession(SessionDto sessionDto);
    void deleteSession(Long id);
}
