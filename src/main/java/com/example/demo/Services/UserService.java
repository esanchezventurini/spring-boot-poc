package com.example.demo.Services;

import com.example.demo.DAO.UserRepository;
import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import com.example.demo.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository realUserRepository) {
        this.userRepository = realUserRepository;
    }

    public List<User> getUsers(Optional<Gender> gender) {
        var users = userRepository.findAll();

        return gender.isPresent() ?
                users.stream().filter(x -> x.getGender().equals(gender.get())).collect(Collectors.toList())
                : users;
    }

    public User getUser(long userId) {
        return getUserIfValid(userId);
    }

    public void updateUser(User user) {
        validateUserExists(user.getId());
        userRepository.save(user);
    }

    public void removeUser(long userId) {
        validateUserExists(userId);
        userRepository.deleteById(userId);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User addVehicle(Vehicle vehicle, long userId) {
        var user = getUserIfValid(userId);
        user.addVehicle(vehicle);
        return userRepository.save(user);
    }

    public List<Vehicle> getUserVehicles(long userId) {
        var user = this.getUserIfValid(userId);
        return user.getVehicles();
    }

    //TODO: Move to Searcher
    private User getUserIfValid(long userId) {
        var optionalUser = userRepository.findById(userId);
        return validateUser(optionalUser);
    }

    //TODO: Move to Searcher
    private User validateUser(Optional<User> user) {
        if(user.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
        return user.get();
    }

    private void validateUserExists(long id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }
}
