package com.techm.vsm.repository;

import com.techm.vsm.model.ServiceRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepresentativeRepository extends JpaRepository<ServiceRepresentative, Long> {
    // Custom queries if needed
}
