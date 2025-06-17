package com.techm.vsm.repository;

import com.techm.vsm.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByStatus(String status);

    List<ServiceRecord> findByServiceRepresentativeId(Long serviceRepId);
}
