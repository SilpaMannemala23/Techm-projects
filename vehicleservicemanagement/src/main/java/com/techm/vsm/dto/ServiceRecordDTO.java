package com.techm.vsm.dto;

import java.util.Date;
import java.util.List;

public class ServiceRecordDTO {
    private Long id;
    private Date serviceDate;
    private String status;
    private Long vehicleId;
    private Long serviceRepresentativeId;
    private List<String> serviceItems;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getServiceDate() {
        return serviceDate;
    }
    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
    public Long getServiceRepresentativeId() {
        return serviceRepresentativeId;
    }
    public void setServiceRepresentativeId(Long serviceRepresentativeId) {
        this.serviceRepresentativeId = serviceRepresentativeId;
    }
    public List<String> getServiceItems() {
        return serviceItems;
    }
    public void setServiceItems(List<String> serviceItems) {
        this.serviceItems = serviceItems;
    }
}
