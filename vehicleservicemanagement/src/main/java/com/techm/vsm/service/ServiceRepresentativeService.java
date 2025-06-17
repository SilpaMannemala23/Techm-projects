package com.techm.vsm.service;

import com.techm.vsm.model.ServiceRepresentative;

import java.util.List;
import java.util.Optional;

public interface ServiceRepresentativeService {

    ServiceRepresentative saveServiceRep(ServiceRepresentative serviceRep);

    ServiceRepresentative updateServiceRep(Long id, ServiceRepresentative serviceRep);

    void deleteServiceRep(Long id);

    Optional<ServiceRepresentative> getServiceRepById(Long id);

    List<ServiceRepresentative> getAllServiceReps();
}
