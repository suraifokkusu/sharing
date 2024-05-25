package com.example.sharing.service;

import com.example.sharing.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();

    //Функционал валюты
    @Transactional
    void addCurrency(Long userId, BigDecimal amount);

    @Transactional
    void deductCurrency(Long userId, BigDecimal amount);
}
