package com.example.sharing.dto;

import lombok.Data;

//@Data
public class UserSkillDto {
    private Long userId;
    private Long skillId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}