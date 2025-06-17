package com.techm.vsm.controller;

import com.techm.vsm.model.ServiceRecord;
import com.techm.vsm.service.ServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-records")
public class ServiceRecordController {

    @Autowired
    private ServiceRecordService serviceRecordService;

    // Get all records
    @GetMapping
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordService.getAllServiceRecords();
    }

    // Get record by id
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getById(@PathVariable Long id) {
        return serviceRecordService.getServiceRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new service record
    @PostMapping
    public ServiceRecord createServiceRecord(@RequestBody ServiceRecord serviceRecord) {
        return serviceRecordService.saveServiceRecord(serviceRecord);
    }

    // Update service record
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable Long id, @RequestBody ServiceRecord serviceRecord) {
        return serviceRecordService.getServiceRecordById(id)
                .map(existing -> {
                    serviceRecord.setId(id);
                    ServiceRecord updated = serviceRecordService.saveServiceRecord(serviceRecord);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete service record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRecord(@PathVariable Long id) {
        if (serviceRecordService.getServiceRecordById(id).isPresent()) {
            serviceRecordService.deleteServiceRecord(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get service records by status (due, under servicing, completed)
    @GetMapping("/status/{status}")
    public List<ServiceRecord> getByStatus(@PathVariable String status) {
        return serviceRecordService.getServiceRecordsByStatus(status);
    }

    // Schedule service for a vehicle by admin: needs vehicleId, serviceRepId and date info, you can enhance this based on frontend needs
    @PostMapping("/schedule")
    public ResponseEntity<ServiceRecord> scheduleService(@RequestParam Long vehicleId, @RequestParam Long serviceRepId) {
        ServiceRecord record = serviceRecordService.scheduleService(vehicleId, serviceRepId);
        if (record != null) {
            return ResponseEntity.ok(record);
        }
        return ResponseEntity.badRequest().build();
    }

    // Add Bill of Material items to service record by service advisor
    // This requires a specific API design; can be an endpoint with serviceRecordId and workItem details
    // For now, assume a simple update call with updated ServiceRecord
}
