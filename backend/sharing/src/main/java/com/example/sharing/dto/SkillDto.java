package com.example.sharing.dto;

public class SkillDto {
    private Long skillId;
    private String skillName;

    // Геттеры и сеттеры
    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
