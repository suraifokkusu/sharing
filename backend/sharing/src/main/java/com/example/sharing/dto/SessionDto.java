package com.example.sharing.dto;

import java.time.LocalDateTime;

public class SessionDto {
    private Long sessionId;
    private Long adId;
    private LocalDateTime sessionDate;
    private String platform;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

// Геттеры и сеттеры
    // ... остальные геттеры и сеттеры
}
