package com.example.sharing.serviceImpl;

import com.example.sharing.dto.UserDto;
import com.example.sharing.entity.Advertisement;
import com.example.sharing.entity.Transaction;
import com.example.sharing.entity.User;
import com.example.sharing.entity.UserSkill;
import com.example.sharing.repository.AdvertisementRepository;
import com.example.sharing.repository.TransactionRepository;
import com.example.sharing.repository.UserRepository;
import com.example.sharing.repository.UserSkillRepository;
import com.example.sharing.mapper.UserMapper;
import com.example.sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("Creating user with data: {}", userDto);
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        logger.info("User created with id: {}", savedUser.getUserId());
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        logger.info("Fetched user: {}", user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        logger.info("Updating user with data: {}", userDto);
        User existingUser = userRepository.findById(userDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntity(existingUser, userDto);
        User updatedUser = userRepository.save(existingUser);
        logger.info("User updated: {}", updatedUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        try {
            logger.info("Attempting to delete user with id: {}", id);
            // Удаление объявлений пользователя перед удалением самого пользователя
            List<Advertisement> ads = advertisementRepository.findByUser_UserId(id);
            for (Advertisement ad : ads) {
                ad.getSessions().clear(); // Очистка связанных сессий
                advertisementRepository.delete(ad); // Удаление объявления
            }

            // Удаление навыков пользователя перед удалением самого пользователя
            List<UserSkill> userSkills = userSkillRepository.findByUser_UserId(id);
            userSkillRepository.deleteAll(userSkills);

            // Удаление транзакций, связанных с пользователем, перед удалением самого пользователя
            List<Transaction> transactionsAsSender = transactionRepository.findBySender_UserId(id);
            transactionRepository.deleteAll(transactionsAsSender);
            List<Transaction> transactionsAsReceiver = transactionRepository.findByReceiver_UserId(id);
            transactionRepository.deleteAll(transactionsAsReceiver);

            userRepository.deleteById(id);
            logger.info("Successfully deleted user with id: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting user with id: {}", id, e);
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Fetched {} users", userDtos.size());
        return userDtos;
    }

    //Функционал валюты
    @Override
    @Transactional
    public void addCurrency(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setCurrencyBalance(user.getCurrencyBalance().add(amount));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deductCurrency(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getCurrencyBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        user.setCurrencyBalance(user.getCurrencyBalance().subtract(amount));
        userRepository.save(user);
    }
}
