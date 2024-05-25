package com.example.sharing.controller;

import com.example.sharing.dto.UserDto;
import com.example.sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/add-currency")
    public ResponseEntity<Void> addCurrency(@PathVariable Long id, @RequestParam BigDecimal amount) {
        userService.addCurrency(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deduct-currency")
    public ResponseEntity<Void> deductCurrency(@PathVariable Long id, @RequestParam BigDecimal amount) {
        userService.deductCurrency(id, amount);
        return ResponseEntity.ok().build();
    }
}
