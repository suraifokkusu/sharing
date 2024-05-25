package com.example.sharing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ad_skills")

@IdClass(AdSkillId.class)
public class AdSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Advertisement ad;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public Advertisement getAd() {
        return ad;
    }

    public void setAd(Advertisement ad) {
        this.ad = ad;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}