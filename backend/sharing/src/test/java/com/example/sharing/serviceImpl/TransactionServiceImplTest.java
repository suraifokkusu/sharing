package com.example.sharing.serviceImpl;

import com.example.sharing.dto.TransactionDto;
import com.example.sharing.entity.Transaction;
import com.example.sharing.mapper.TransactionMapper;
import com.example.sharing.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testCreateTransaction() {
        // Given
        TransactionDto dto = new TransactionDto();
        dto.setAmount(100.0);
        Transaction transaction = new Transaction();
        when(transactionMapper.toEntity(dto)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toDto(transaction)).thenReturn(dto);

        // When
        TransactionDto result = transactionService.createTransaction(dto);

        // Then
        assertNotNull(result);
        assertEquals(100.0, result.getAmount());
        verify(transactionRepository).save(transaction);
        verify(transactionMapper).toDto(transaction);
    }
}
