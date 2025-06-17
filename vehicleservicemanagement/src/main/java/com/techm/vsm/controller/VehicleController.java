package com.techm.vsm.controller;

import com.techm.vsm.dto.VehicleDTO;
import com.techm.vsm.model.Customer;
import com.techm.vsm.model.Vehicle;
import com.techm.vsm.repository.CustomerRepository;
import com.techm.vsm.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Create new vehicle linked to a customer
    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDTO vehicleRequest) {
        Customer customer = customerRepository.findById(vehicleRequest.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found with id " + vehicleRequest.getCustomerId()));

        Vehicle vehicle = Vehicle.builder()
                .registrationNumber(vehicleRequest.getRegistrationNumber())
                .model(vehicleRequest.getModel())
                .manufacturer(vehicleRequest.getManufacturer())
                .year(vehicleRequest.getYear())
                .customer(customer)
                .build();

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return ResponseEntity.ok(savedVehicle);
    }
    

    // Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
