package com.example.demo.Services;

import com.example.demo.DAO.UserRepository;
import com.example.demo.DAO.VehicleRepository;
import com.example.demo.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final Searcher<Vehicle> vehicleSearcher;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, Searcher<Vehicle> vehicleSearcher) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleSearcher = vehicleSearcher;
    }

    public void removeVehicle(long vehicleId) {
        vehicleSearcher.validateExistence(vehicleId, vehicleRepository);
        vehicleRepository.deleteById(vehicleId);
    }
}
