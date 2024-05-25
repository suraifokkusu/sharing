package com.example.sharing.repository;

import com.example.sharing.entity.AdSkill;
import com.example.sharing.entity.AdSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdSkillRepository extends JpaRepository<AdSkill, AdSkillId> {
    List<AdSkill> findByAd_AdId(Long adId); // Метод для поиска навыков объявления по adId
    List<AdSkill> findBySkill_SkillId(Long skillId); // Метод для поиска навыков по skillId
}