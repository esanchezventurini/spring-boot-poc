package com.example.demo.Controllers;

import com.example.demo.Model.User;
import com.example.demo.Model.Vehicle;
import com.example.demo.Services.UserService;
import com.example.demo.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        path = "/api/users/{userId}/vehicles"
)
public class VehicleController {
    private final UserService userService;
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(UserService userService, VehicleService vehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<Vehicle>> getUserVehicles(@PathVariable("userId") long userId) {
        return ResponseEntity.ok(userService.getUserVehicles(userId));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<User> addVehicle(@PathVariable("userId") long userId, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(userService.addVehicle(vehicle, userId));
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{vehicleId}",
            produces = APPLICATION_JSON_VALUE
    )
    void removeVehicle(@PathVariable("vehicleId") long vehicleId) {
        vehicleService.removeVehicle(vehicleId);
    }
}
