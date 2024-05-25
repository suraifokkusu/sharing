package com.example.sharing.dto;

import java.math.BigDecimal;

public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String telegramId;
    private String profileText;
    private BigDecimal rating;
    private BigDecimal currencyBalance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getProfileText() {
        return profileText;
    }

    public void setProfileText(String profileText) {
        this.profileText = profileText;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public BigDecimal getCurrencyBalance() {
        return currencyBalance;
    }

    public void setCurrencyBalance(BigDecimal currencyBalance) {
        this.currencyBalance = currencyBalance;
    }
}
