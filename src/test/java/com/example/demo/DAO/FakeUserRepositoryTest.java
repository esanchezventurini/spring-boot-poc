package com.example.demo.DAO;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FakeUserRepositoryTest {
    private static IUserRepository userRepository;
    private static UUID userId = UUID.fromString("0f14d0ab-9605-4a62-a9e4-5ed26688389b");
    private static UUID incorrectUserId = UUID.fromString("0f14d0ab-9605-4a62-a1e4-5ed26688389b");
    private static User user2;


    @BeforeEach
    public void setUp() {
        userRepository = new FakeUserRepository();
        user2 = new User(UUID.randomUUID(), "Juan", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com");
    }

    @Test
    public void getAllUsers() {
        assertEquals(1, userRepository.getAllUsers().size());
    }

    @Test
    public void getExistentUser() {
        var user = userRepository.getUser(userId).get();
        assertEquals(user.getLastName(), "Carlos");
        assertEquals(user.getFirstName(), "Juan");
        assertEquals(user.getGender(), Gender.MALE);
        assertEquals(user.getAge(), 30);
        assertEquals(user.getEmail(), "juancarlos@gmail.com");
    }

    @Test
    public void getNonExistentUser() {
        assertFalse(userRepository.getUser(incorrectUserId).isPresent());
    }

    @Test
    public void updateUser() {
        userRepository.insertUser(user2);
        user2.setAge(31);
        userRepository.updateUser(user2);
        var updatedUser = userRepository.getUser(user2.getId());
        assertEquals(updatedUser.get().getAge(), 31);
    }

    @Test
    public void removeUser() {
        assertTrue(userRepository.getUser(userId).isPresent());
        userRepository.removeUser(userId);
        assertFalse(userRepository.getUser(userId).isPresent());
    }

    @Test
    public void insertUser() {
        int numberOfUsers = userRepository.getAllUsers().size();
        userRepository.insertUser(user2);
        assertEquals(numberOfUsers + 1, userRepository.getAllUsers().size());
    }
}