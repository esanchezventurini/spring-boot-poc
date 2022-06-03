package com.example.demo.Services;

import com.example.demo.DAO.IUserRepository;
import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(Optional<Gender> gender) {
        var users = userRepository.getAllUsers();
        return gender.isPresent() ?
                users.stream().filter(x -> x.getGender().equals(gender.get())).collect(Collectors.toList())
                : users;
    }

    public User getUser(UUID userId) {
        var user = userRepository.getUser(userId);
        validateUser(user);
        return user.get();
    }

    public void updateUser(User user) {
        if(!userRepository.updateUser(user)) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }

    public void removeUser(UUID userId) {
        if(!userRepository.removeUser(userId)) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }

    public void addUser(User user) {
        userRepository.insertUser(user);
    }

    private void validateUser(Optional<User> user) {
        if(user.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }
}
