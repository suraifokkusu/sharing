package com.example.sharing.entity;

import java.io.Serializable;

public class AdSkillId implements Serializable {
    private Long ad;
    private Long skill;

    // Конструктор без параметров
    public AdSkillId() {}

    // Конструктор с параметрами
    public AdSkillId(Long ad, Long skill) {
        this.ad = ad;
        this.skill = skill;
    }

    // Геттеры и сеттеры
    public Long getAd() {
        return ad;
    }

    public void setAd(Long ad) {
        this.ad = ad;
    }

    public Long getSkill() {
        return skill;
    }

    public void setSkill(Long skill) {
        this.skill = skill;
    }

    // Переопределение equals() и hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdSkillId adSkillId = (AdSkillId) o;

        if (!ad.equals(adSkillId.ad)) return false;
        return skill.equals(adSkillId.skill);
    }

    @Override
    public int hashCode() {
        int result = ad.hashCode();
        result = 31 * result + skill.hashCode();
        return result;
    }
}