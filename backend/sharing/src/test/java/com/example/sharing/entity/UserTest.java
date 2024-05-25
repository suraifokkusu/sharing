package com.example.sharing.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setUserId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setTelegramId("john_doe");
        user.setProfileText("Experienced programmer");
        user.setRating(new BigDecimal("4.5"));
        user.setCurrencyBalance(new BigDecimal("100.00"));

        assertEquals(1L, user.getUserId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("john_doe", user.getTelegramId());
        assertEquals("Experienced programmer", user.getProfileText());
        assertEquals(new BigDecimal("4.5"), user.getRating());
        assertEquals(new BigDecimal("100.00"), user.getCurrencyBalance());
    }
}
