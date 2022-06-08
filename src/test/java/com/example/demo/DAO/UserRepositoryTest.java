package com.example.demo.DAO;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private static User user2;


    @BeforeEach
    public void setUp() {
        user2 = new User("Juan", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com");
    }

    @Test
    public void getExistentUser() {
        userRepository.save(user2);
        var user = userRepository.findById(user2.getId()).get();
        assertEquals(user.getLastName(), "Carlos");
        assertEquals(user.getFirstName(), "Juan");
        assertEquals(user.getGender(), Gender.MALE);
        assertEquals(user.getAge(), 30);
        assertEquals(user.getEmail(), "juancarlos@gmail.com");
    }

    @Test
    public void updateUser() {
        userRepository.save(user2);
        var userId = user2.getId();
        user2.setAge(31);
        userRepository.save(user2);
        var updatedUser = userRepository.findById(userId);
        assertEquals(updatedUser.get().getAge(), 31);
    }

    @Test
    public void removeUser() {
        userRepository.save(user2);
        var userId = user2.getId();
        assertTrue(userRepository.findById(userId).isPresent());
        userRepository.deleteById(userId);
        assertFalse(userRepository.findById(userId).isPresent());
    }

    @Test
    public void insertUser() {
        int numberOfUsers = userRepository.findAll().size();
        userRepository.save(user2);
        assertEquals(numberOfUsers + 1, userRepository.findAll().size());
    }
}