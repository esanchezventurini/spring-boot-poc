package com.example.demo.DAO;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeUserRepository implements IUserRepository {

    public FakeUserRepository(List<User> database) {
        this.database = database;
    }

    private List<User> database;

    public FakeUserRepository() {
        database = new ArrayList<>();
        database.add(new User(UUID.fromString("0f14d0ab-9605-4a62-a9e4-5ed26688389b"), "Juan", "Carlos", Gender.MALE, 30, "juancarlos@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(database);
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        return database.stream()
                        .filter(x -> x.getId().equals(userId))
                        .findFirst();
    }

    @Override
    public boolean updateUser(User user) {
        if(this.removeById(user.getId())){
            return database.add(user);
        }
        return false;
    }

    @Override
    public boolean removeUser(UUID userId) {
        return this.removeById(userId);
    }

    @Override
    public void insertUser(User user) {
        database.add(user);
    }

    private boolean removeById(UUID userId) {
        return database.removeIf(x -> x.getId().equals(userId));
    }
}
