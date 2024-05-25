package com.example.sharing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "reviews")
@Getter
@Setter

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    private Integer rating;
    private String comment;

}
