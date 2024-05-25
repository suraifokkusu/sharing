//package com.example.sharing.controller;
//
//import com.example.sharing.dto.SessionDto;
//import com.example.sharing.service.SessionService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@WebMvcTest(SessionController.class)
//public class SessionControllerIT {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SessionService sessionService;
//
//    @Test
//    public void testCreateSession() throws Exception {
//        // Given
//        SessionDto dto = new SessionDto();
//        dto.setSessionDate(java.sql.Timestamp.valueOf("2023-01-01 12:00:00"));
//        when(sessionService.createSession(any())).thenReturn(dto);
//
//        // When & Then
//        mockMvc.perform(post("/api/sessions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"sessionDate\":\"2023-01-01T12:00:00\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.sessionDate").value("2023-01-01T12:00:00"))
//                .andDo(print());
//
//        verify(sessionService).createSession(any());
//    }
//}
