//package com.example.sharing.serviceImpl;
//
//import com.example.sharing.dto.SessionDto;
//import com.example.sharing.entity.Session;
//import com.example.sharing.mapper.SessionMapper;
//import com.example.sharing.repository.SessionRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SessionServiceImplTest {
//
//    @Mock
//    private SessionRepository sessionRepository;
//
//    @Mock
//    private SessionMapper sessionMapper;
//
//    @InjectMocks
//    private SessionServiceImpl sessionService;
//
//    @Test
//    public void testCreateSession() {
//        // Given
//        SessionDto dto = new SessionDto();
//        dto.setSessionDate(java.sql.Timestamp.valueOf("2023-01-01 12:00:00"));
//        Session session = new Session();
//        when(sessionMapper.toEntity(dto)).thenReturn(session);
//        when(sessionRepository.save(session)).thenReturn(session);
//        when(sessionMapper.toDto(session)).thenReturn(dto);
//
//        // When
//        SessionDto result = sessionService.createSession(dto);
//
//        // Then
//        assertNotNull(result);
//        assertNotNull(result.getSessionDate());
//        verify(sessionRepository).save(session);
//        verify(sessionMapper).toDto(session);
//    }
//}
