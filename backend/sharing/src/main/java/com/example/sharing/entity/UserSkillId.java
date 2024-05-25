package com.example.sharing.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserSkillId implements Serializable {
    private Long user;
    private Long skill;

    public UserSkillId() {
    }

    public UserSkillId(Long user, Long skill) {
        this.user = user;
        this.skill = skill;
    }

    // Getters, setters, hashCode, equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkillId that = (UserSkillId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, skill);
    }

    // Getters and setters

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getSkill() {
        return skill;
    }

    public void setSkill(Long skill) {
        this.skill = skill;
    }
}