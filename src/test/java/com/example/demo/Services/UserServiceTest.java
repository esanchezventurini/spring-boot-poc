package com.example.demo.Services;

import com.example.demo.DAO.FakeUserRepository;
import com.example.demo.DAO.IUserRepository;
import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userService;
    @Mock
    private IUserRepository userRepository;
    private User juan;
    private User juan2;
    private List<User> users;
    private AutoCloseable closeable;

    @BeforeEach
    void init() {
        //Create mock from clas FakeUserRepository
        userRepository = mock(FakeUserRepository.class);

        userService = new UserService(userRepository);
        juan = new User(UUID.randomUUID(), "Juan1", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com");
        juan2 = new User(UUID.randomUUID(), "Juan", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com");
        users = Arrays.asList(juan, juan2);
    }

    @Test
    void getUsers() {
        //Defines return for mock method getAllUsers()
        given(userRepository.getAllUsers()).willReturn(users);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        assertEquals(userService.getUsers(Optional.empty()).size(), 2);

        //Verify that the method getAllUsers() of mock userRepository was called.
        verify(userRepository).getAllUsers();
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
        //Defines return for mock method getAllUsers()
        given(userRepository.updateUser(juan)).willReturn(true);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        userService.updateUser(juan);

        verify(userRepository).updateUser(captor.capture());

        var user = captor.getValue();
        assertEquals(user.getFirstName(), "Juan1");
    }

    @Test
    void removeUser() {
    }

    @Test
    void addUser() { }
}