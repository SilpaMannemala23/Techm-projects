package com.techm.vsm.service;

import com.techm.vsm.model.Vehicle;
import com.techm.vsm.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Optional<Vehicle> existing = vehicleRepository.findById(id);
        if(existing.isPresent()) {
            Vehicle v = existing.get();
            v.setManufacturer(vehicle.getManufacturer());
            v.setModel(vehicle.getModel());
            v.setRegistrationNumber(vehicle.getRegistrationNumber());
            v.setYear(vehicle.getYear());
            v.setCustomer(vehicle.getCustomer());
            return vehicleRepository.save(v);
        } else {
            throw new RuntimeException("Vehicle not found with id " + id);
        }
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
