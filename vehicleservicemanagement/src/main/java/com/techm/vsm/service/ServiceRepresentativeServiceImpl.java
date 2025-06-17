package com.techm.vsm.service;

import com.techm.vsm.model.ServiceRepresentative;
import com.techm.vsm.repository.ServiceRepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRepresentativeServiceImpl implements ServiceRepresentativeService {

    @Autowired
    private ServiceRepresentativeRepository serviceRepresentativeRepository;

    @Override
    public ServiceRepresentative saveServiceRep(ServiceRepresentative serviceRep) {
        return serviceRepresentativeRepository.save(serviceRep);
    }

    @Override
    public ServiceRepresentative updateServiceRep(Long id, ServiceRepresentative serviceRep) {
        Optional<ServiceRepresentative> existing = serviceRepresentativeRepository.findById(id);
        if(existing.isPresent()) {
            ServiceRepresentative sr = existing.get();
            sr.setName(serviceRep.getName());
            sr.setEmail(serviceRep.getEmail());
            sr.setPhone(serviceRep.getPhone());
            return serviceRepresentativeRepository.save(sr);
        } else {
            throw new RuntimeException("Service Representative not found with id " + id);
        }
    }

    @Override
    public void deleteServiceRep(Long id) {
        serviceRepresentativeRepository.deleteById(id);
    }

    @Override
    public Optional<ServiceRepresentative> getServiceRepById(Long id) {
        return serviceRepresentativeRepository.findById(id);
    }

    @Override
    public List<ServiceRepresentative> getAllServiceReps() {
        return serviceRepresentativeRepository.findAll();
    }
}
