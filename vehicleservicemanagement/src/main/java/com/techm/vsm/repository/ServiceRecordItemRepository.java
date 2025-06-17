package com.techm.vsm.repository;

import com.techm.vsm.model.ServiceRecordItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRecordItemRepository extends JpaRepository<ServiceRecordItem, Long> {
}
