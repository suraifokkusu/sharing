package com.example.sharing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "advertisements")

public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 50)
    private String adType; // 'offer' или 'request'

    @Column(nullable = false, length = 50)
    private String status;  // 'active', 'completed', 'cancelled'

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions; // Добавлено поле для сессий

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdSkill> adSkills; // Добавлено поле для навыков объявления

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<AdSkill> getAdSkills() {
        return adSkills;
    }

    public void setAdSkills(List<AdSkill> adSkills) {
        this.adSkills = adSkills;
    }
}

