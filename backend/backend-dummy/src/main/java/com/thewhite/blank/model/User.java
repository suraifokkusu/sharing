package com.thewhite.blank.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Generated by Thanos
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    String nickname;

    String password;

    String displayName;

    String phone;

    Long balanceOnWallet;

    UUID avatarId;

    String description;

    @JoinTable(name = "learn_skill")
    @ManyToMany
    @Builder.Default
    List<Skill> learn = new ArrayList<>();

    @JoinTable(name = "teach_skill")
    @ManyToMany
    @Builder.Default
    List<Skill> teach = new ArrayList<>();
}