package com.example.sharing.controller;

import com.example.sharing.dto.TransactionDto;
import com.example.sharing.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(TransactionController.class)
public class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void testCreateTransaction() throws Exception {
        // Given
        TransactionDto dto = new TransactionDto();
        dto.setAmount(100.0);
        when(transactionService.createTransaction(any())).thenReturn(dto);

        // When & Then
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0))
                .andDo(print());

        verify(transactionService).createTransaction(any());
    }
}
