package com.example.demo.Services;

import com.example.demo.DAO.VehicleRepository;
import com.example.demo.Model.User;
import com.example.demo.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void removeVehicle(long vehicleId) {
        this.validateUserExists(vehicleId);
        vehicleRepository.deleteById(vehicleId);
    }

    //TODO: Move to Searcher
    private void validateUserExists(long id) {
        if(!vehicleRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "User not found.");
        }
    }
}
