package com.example.sharing.repository;

import com.example.sharing.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySender_UserId(Long userId); // Метод для поиска транзакций по отправителю
    List<Transaction> findByReceiver_UserId(Long userId); // Метод для поиска транзакций по получателю
}
