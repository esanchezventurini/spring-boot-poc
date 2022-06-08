package com.example.demo.Controllers;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;
import com.example.demo.Services.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//@Validated
//@Path("/api/RestEasy/users")
//@RestController
public class UserControllerResteasy {
    private final UserService userService;

    @Autowired
    public UserControllerResteasy(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("{userId}")
    @Produces(APPLICATION_JSON_VALUE)
    public User getUser(@PathParam("userId") long userId) {
        return userService.getUser(userId);
    }

    @GET
    @Produces(APPLICATION_JSON_VALUE)
    public List<User> getUsers(@QueryParam("gender") Gender gender) {
        return userService.getUsers(Optional.ofNullable(gender));
    }

    @POST
    @Produces(APPLICATION_JSON_VALUE)
    @Consumes(APPLICATION_JSON_VALUE)
    public void addUser(@Valid User user) {
        userService.addUser(user);
    }

    @PUT
    @Produces(APPLICATION_JSON_VALUE)
    @Consumes(APPLICATION_JSON_VALUE)
    public void updateUser(@Valid User user) {
        userService.updateUser(user);
    }

    @DELETE
    @Path("{userId}")
    @Produces(APPLICATION_JSON_VALUE)
    public void deleteUser(@PathParam("userId") long userId) {
        userService.removeUser(userId);
    }
}
