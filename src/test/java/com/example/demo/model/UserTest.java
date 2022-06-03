package com.example.demo.model;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    User adult;
    User kid;

    public UserTest() {
        adult = new User(UUID.randomUUID(), "Juan1", "Carlos", Gender.MALE, 20, "juancarlos@gmail.com");
        kid = new User(UUID.randomUUID(), "Juan", "Carlos", Gender.MALE, 17, "juancarlos@gmail.com");
    }

/*
    @Test
    public void userIsAdult() {
        assertTrue(adult.isAdult());
    }

    @Test
    public void userIsNotAdult() {
        assertFalse(kid.isAdult());
    }*/
}
