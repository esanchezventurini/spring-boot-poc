package com.example.demo.Services;

import com.example.demo.DAO.UserRepository;
import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import com.example.demo.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final Searcher<User> userSearcher;

    @Autowired
    public UserService(UserRepository userRepository, Searcher<User> userSearcher) {
        this.userRepository = userRepository;
        this.userSearcher = userSearcher;
    }

    public List<User> getUsers(Optional<Gender> gender) {
        var users = userRepository.findAll();

        return gender.isPresent() ?
                users.stream().filter(x -> x.getGender().equals(gender.get())).collect(Collectors.toList())
                : users;
    }

    public User getUser(long userId) {
        return userSearcher.getIfValid(userId, userRepository);
    }

    public void updateUser(User user) {
        userSearcher.validateExistence(user.getId(), userRepository);
        userRepository.save(user);
    }

    public void removeUser(long userId) {
        userSearcher.validateExistence(userId, userRepository);
        userRepository.deleteById(userId);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User addVehicle(Vehicle vehicle, long userId) {
        var user = userSearcher.getIfValid(userId, userRepository);
        user.addVehicle(vehicle);
        return userRepository.save(user);
    }

    public List<Vehicle> getUserVehicles(long userId) {
        var user = userSearcher.getIfValid(userId, userRepository);
        return user.getVehicles();
    }
}
