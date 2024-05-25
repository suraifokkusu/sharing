package com.example.sharing.serviceImpl;

import com.example.sharing.dto.SessionDto;
import com.example.sharing.entity.Session;
import com.example.sharing.mapper.SessionMapper;
import com.example.sharing.repository.SessionRepository;
import com.example.sharing.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public SessionDto createSession(SessionDto sessionDto) {
        logger.info("Creating session with data: {}", sessionDto);
        Session session = sessionMapper.toEntity(sessionDto);
        Session savedSession = sessionRepository.save(session);
        logger.info("Session created with id: {}", savedSession.getSessionId());
        return sessionMapper.toDto(savedSession);
    }

    @Override
    public SessionDto getSessionById(Long id) {
        logger.info("Fetching session with id: {}", id);
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        logger.info("Fetched session: {}", session);
        return sessionMapper.toDto(session);
    }

    @Override
    public SessionDto updateSession(SessionDto sessionDto) {
        logger.info("Updating session with data: {}", sessionDto);
        Session existingSession = sessionRepository.findById(sessionDto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));
        sessionMapper.updateEntity(existingSession, sessionDto);
        Session updatedSession = sessionRepository.save(existingSession);
        logger.info("Session updated: {}", updatedSession);
        return sessionMapper.toDto(updatedSession);
    }

    @Override
    @Transactional
    public void deleteSession(Long id) {
        try {
            logger.info("Attempting to delete session with id: {}", id);
            sessionRepository.deleteById(id);
            logger.info("Successfully deleted session with id: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting session with id: {}", id, e);
            throw new RuntimeException("Failed to delete session", e);
        }
    }

    @Override
    public List<SessionDto> getAllSessions() {
        logger.info("Fetching all sessions");
        List<Session> sessions = sessionRepository.findAll();
        List<SessionDto> sessionDtos = sessions.stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} sessions", sessionDtos.size());
        return sessionDtos;
    }
}
