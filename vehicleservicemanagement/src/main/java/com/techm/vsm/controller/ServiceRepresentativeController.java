package com.techm.vsm.controller;

import com.techm.vsm.model.ServiceRepresentative;
import com.techm.vsm.service.ServiceRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-reps")
public class ServiceRepresentativeController {

    @Autowired
    private ServiceRepresentativeService serviceRepService;

    @GetMapping
    public List<ServiceRepresentative> getAllServiceReps() {
        return serviceRepService.getAllServiceReps();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRepresentative> getServiceRepById(@PathVariable Long id) {
        return serviceRepService.getServiceRepById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ServiceRepresentative createServiceRep(@RequestBody ServiceRepresentative serviceRep) {
        return serviceRepService.saveServiceRep(serviceRep);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRepresentative> updateServiceRep(@PathVariable Long id, @RequestBody ServiceRepresentative serviceRep) {
        return serviceRepService.getServiceRepById(id)
                .map(existing -> {
                    serviceRep.setId(id);
                    ServiceRepresentative updated = serviceRepService.saveServiceRep(serviceRep);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRep(@PathVariable Long id) {
        if (serviceRepService.getServiceRepById(id).isPresent()) {
            serviceRepService.deleteServiceRep(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
