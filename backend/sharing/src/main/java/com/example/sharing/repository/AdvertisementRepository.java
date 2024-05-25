package com.example.sharing.repository;

import com.example.sharing.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByUser_UserId(Long userId); // Корректный метод для поиска объявлений по userId
}
