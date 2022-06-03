package com.example.demo.clientproxy;

import com.example.demo.Model.Gender;
import com.example.demo.Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface UserControllerV1 {
    @GET
    @Path("{userId}")
    @Produces(APPLICATION_JSON_VALUE)
    User getUser(@PathParam("userId") UUID userId);

    @GET
    @Produces(APPLICATION_JSON_VALUE)
    List<User> getUsers(@QueryParam("gender") Gender gender);

    @POST
    @Produces(APPLICATION_JSON_VALUE)
    @Consumes(APPLICATION_JSON_VALUE)
    void addUser(User user);

    @PUT
    @Produces(APPLICATION_JSON_VALUE)
    @Consumes(APPLICATION_JSON_VALUE)
    void updateUser(User user);

    @DELETE
    @Path("{userId}")
    @Produces(APPLICATION_JSON_VALUE)
    void deleteUser(@PathParam("userId") UUID userId);
}