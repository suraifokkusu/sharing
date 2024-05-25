package com.example.sharing.dto;

import java.util.List;

public class AdvertisementDto {
    private Long adId;
    private Long userId;
    private String category;
    private String description;
    private String adType;
    private String status;
    private List<SessionDto> sessions;
    private List<AdSkillDto> adSkills;

    // Геттеры и сеттеры
    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SessionDto> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDto> sessions) {
        this.sessions = sessions;
    }

    public List<AdSkillDto> getAdSkills() {
        return adSkills;
    }

    public void setAdSkills(List<AdSkillDto> adSkills) {
        this.adSkills = adSkills;
    }
}