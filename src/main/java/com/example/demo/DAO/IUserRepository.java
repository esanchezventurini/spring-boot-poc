package com.example.demo.DAO;

import com.example.demo.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    List<User> getAllUsers();

    Optional<User> getUser(UUID userId);

    boolean updateUser(User user);

    boolean removeUser(UUID userId);

    void insertUser(User user);
}
