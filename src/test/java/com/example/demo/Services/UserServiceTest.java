package com.example.demo.Services;

import com.example.demo.DAO.UserRepository;
import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private User juan;
    private User juan2;
    private List<User> users;
    private AutoCloseable closeable;

    @BeforeEach
    void init() {
        //Create mock from clas FakeUserRepository
        userRepository = mock(UserRepository.class);

        userService = new UserService(userRepository);
        juan = new User("Juan1", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com");
        juan2 = new User("Juan", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com");
        users = Arrays.asList(juan, juan2);
    }

    @Test
    void getUsers() {
        //Defines return for mock method getAllUsers()
        given(userRepository.findAll()).willReturn(users);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        assertEquals(userService.getUsers(Optional.empty()).size(), 2);

        //Verify that the method getAllUsers() of mock userRepository was called.
        verify(userRepository).findAll();
    }

    @Test
    void getUser() {
    }

    /*@Test
    void updateUser() {
        //Defines return for mock method getAllUsers()
        given(userRepository.save(juan)).willReturn(true);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        userService.updateUser(juan);

        verify(userRepository).save(captor.capture());

        var user = captor.getValue();
        assertEquals(user.getFirstName(), "Juan1");
    }*/

    @Test
    void removeUser() {
    }

    @Test
    void addUser() { }
}