package com.example.sharing.repository;

import com.example.sharing.entity.UserSkill;
import com.example.sharing.entity.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {
    List<UserSkill> findByUser_UserId(Long userId); // Метод для поиска навыков пользователя по userId
    List<UserSkill> findBySkill_SkillId(Long skillId); // Метод для поиска навыков по skillId
}
