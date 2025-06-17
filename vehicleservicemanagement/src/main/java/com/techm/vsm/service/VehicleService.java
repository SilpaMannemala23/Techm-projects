package com.techm.vsm.service;

import com.techm.vsm.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Long id, Vehicle vehicle);

    void deleteVehicle(Long id);

    Optional<Vehicle> getVehicleById(Long id);

    List<Vehicle> getAllVehicles();
}
