package com.example.demo.Controllers;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping(
        path = "/api/SpringMVC/users"
)
public class UserControllerSpringMVC {
    private final UserService userService;

    @Autowired
    public UserControllerSpringMVC(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{userId}",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<User> getUser(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<User>> getUsers(@RequestParam(required = false) Gender gender) {
        return ResponseEntity.ok(userService.getUsers(Optional.ofNullable(gender)));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    void updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{userId}",
            produces = APPLICATION_JSON_VALUE
    )
    void deleteUser(@PathVariable("userId") UUID userId) {
        userService.removeUser(userId);
    }
}
