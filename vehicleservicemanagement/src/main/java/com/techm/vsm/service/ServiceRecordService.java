package com.techm.vsm.service;

import com.techm.vsm.model.ServiceRecord;

import java.util.List;
import java.util.Optional;

public interface ServiceRecordService {

    ServiceRecord saveServiceRecord(ServiceRecord serviceRecord);

    ServiceRecord updateServiceRecord(Long id, ServiceRecord serviceRecord);
    
    ServiceRecord scheduleService(Long vehicleId, Long serviceRepId);

    void deleteServiceRecord(Long id);

    Optional<ServiceRecord> getServiceRecordById(Long id);

    List<ServiceRecord> getAllServiceRecords();

    List<ServiceRecord> getServiceRecordsByStatus(String status);

    List<ServiceRecord> getServiceRecordsByServiceRep(Long serviceRepId);
    
    
}
