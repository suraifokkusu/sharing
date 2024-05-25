package com.example.sharing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "skills")
@Getter
@Setter

public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(nullable = false, unique = true)
    private String skillName;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<AdSkill> adSkills;

}
