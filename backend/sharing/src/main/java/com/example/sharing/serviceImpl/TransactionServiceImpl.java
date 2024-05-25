package com.example.sharing.serviceImpl;

import com.example.sharing.dto.TransactionDto;
import com.example.sharing.entity.Transaction;
import com.example.sharing.mapper.TransactionMapper;
import com.example.sharing.repository.TransactionRepository;
import com.example.sharing.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        logger.info("Creating transaction with data: {}", transactionDto);
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        logger.info("Transaction created with id: {}", savedTransaction.getTransactionId());
        return transactionMapper.toDto(savedTransaction);
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        logger.info("Fetching transaction with id: {}", id);
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        logger.info("Fetched transaction: {}", transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto transactionDto) {
        logger.info("Updating transaction with data: {}", transactionDto);
        Transaction existingTransaction = transactionRepository.findById(transactionDto.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionMapper.updateEntity(existingTransaction, transactionDto);
        Transaction updatedTransaction = transactionRepository.save(existingTransaction);
        logger.info("Transaction updated: {}", updatedTransaction);
        return transactionMapper.toDto(updatedTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        try {
            logger.info("Attempting to delete transaction with id: {}", id);
            transactionRepository.deleteById(id);
            logger.info("Successfully deleted transaction with id: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting transaction with id: {}", id, e);
            throw new RuntimeException("Failed to delete transaction", e);
        }
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        logger.info("Fetching all transactions");
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = transactions.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} transactions", transactionDtos.size());
        return transactionDtos;
    }
}
