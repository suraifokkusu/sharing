package com.example.sharing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sessions")
@Getter
@Setter

public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Advertisement advertisement;

    private LocalDateTime sessionDate;
    private String platform;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}
