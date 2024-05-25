package com.example.sharing.dto;

public class AdSkillDto {
    private Long adId;
    private Long skillId;

    // Геттеры и сеттеры
    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}