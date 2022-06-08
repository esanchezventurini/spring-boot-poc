package com.example.demo.Services;

import com.example.demo.DAO.UserRepository;
import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {
    private UserRepository realUserRepository;

    @Autowired
    public UserService(UserRepository realUserRepository) {
        this.realUserRepository = realUserRepository;
    }

    public List<User> getUsers(Optional<Gender> gender) {
        var users = realUserRepository.findAll();

        return gender.isPresent() ?
                users.stream().filter(x -> x.getGender().equals(gender.get())).collect(Collectors.toList())
                : users;
    }

    public User getUser(long userId) {
        var user = realUserRepository.findById(userId);
        validateUser(user);
        return user.get();
    }

    public void updateUser(User user) {
        validateUserExists(user.getId());
        realUserRepository.save(user);
    }

    public void removeUser(long userId) {
        validateUserExists(userId);
        realUserRepository.deleteById(userId);
    }

    public void addUser(User user) {
        realUserRepository.save(user);
    }

    private void validateUser(Optional<User> user) {
        if(user.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }

    private void validateUserExists(long id) {
        if(!realUserRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }
}
