package com.example.sharing.service;

import com.example.sharing.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto getTransactionById(Long id);
    TransactionDto updateTransaction(TransactionDto transactionDto);
    void deleteTransaction(Long id);
    List<TransactionDto> getAllTransactions();
}
