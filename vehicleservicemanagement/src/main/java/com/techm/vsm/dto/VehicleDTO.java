package com.techm.vsm.dto;

import lombok.Data;

@Data
public class VehicleDTO {
    private String registrationNumber;
    private String model;
    private String manufacturer;
    private Integer year;
    private Long customerId;  // only the ID, not whole customer object
}
