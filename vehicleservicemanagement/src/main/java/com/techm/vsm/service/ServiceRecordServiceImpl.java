package com.techm.vsm.service;

import com.techm.vsm.model.ServiceRecord;
import com.techm.vsm.model.Vehicle;
import com.techm.vsm.model.ServiceRepresentative;
import com.techm.vsm.repository.ServiceRecordRepository;
import com.techm.vsm.repository.VehicleRepository;
import com.techm.vsm.repository.ServiceRepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecordServiceImpl implements ServiceRecordService {

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceRepresentativeRepository serviceRepresentativeRepository;

    @Override
    public ServiceRecord saveServiceRecord(ServiceRecord serviceRecord) {
        return serviceRecordRepository.save(serviceRecord);
    }

    @Override
    public ServiceRecord updateServiceRecord(Long id, ServiceRecord serviceRecord) {
        // Your existing update logic
        serviceRecord.setId(id);
        return serviceRecordRepository.save(serviceRecord);
    }

    @Override
    public ServiceRecord scheduleService(Long vehicleId, Long serviceRepId) {
        // Your existing schedule logic
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        ServiceRepresentative rep = serviceRepresentativeRepository.findById(serviceRepId).orElse(null);

        if (vehicle == null || rep == null) {
            return null;
        }

        ServiceRecord record = ServiceRecord.builder()
                .serviceDate(java.time.LocalDate.now())
                .status("Scheduled")
                .vehicle(vehicle)
                .serviceRepresentative(rep)
                .build();

        return serviceRecordRepository.save(record);
    }

    @Override
    public void deleteServiceRecord(Long id) {
        serviceRecordRepository.deleteById(id);
    }

    @Override
    public Optional<ServiceRecord> getServiceRecordById(Long id) {
        Optional<ServiceRecord> recordOpt = serviceRecordRepository.findById(id);

        if (recordOpt.isPresent()) {
            ServiceRecord record = recordOpt.get();

            // Fetch full Vehicle and set it
            Vehicle vehicle = vehicleRepository.findById(record.getVehicle().getId()).orElse(null);
            record.setVehicle(vehicle);

            // Fetch full ServiceRepresentative and set it
            ServiceRepresentative rep = serviceRepresentativeRepository.findById(record.getServiceRepresentative().getId()).orElse(null);
            record.setServiceRepresentative(rep);

            return Optional.of(record);
        }
        return Optional.empty();
    }

    @Override
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    @Override
    public List<ServiceRecord> getServiceRecordsByStatus(String status) {
        return serviceRecordRepository.findByStatus(status);
    }

    @Override
    public List<ServiceRecord> getServiceRecordsByServiceRep(Long serviceRepId) {
        return serviceRecordRepository.findByServiceRepresentativeId(serviceRepId);
    }
}
