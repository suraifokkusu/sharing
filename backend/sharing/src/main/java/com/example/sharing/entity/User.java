package com.example.sharing.entity;

import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String telegramId;
    @Column(length = 1024)
    private String profileText;
    private BigDecimal rating;  // Используем BigDecimal вместо Double
    private BigDecimal currencyBalance;  // Используем BigDecimal вместо Double
}
