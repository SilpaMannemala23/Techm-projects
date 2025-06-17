package com.techm.vsm.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ServiceRecordItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_record_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ServiceRecord serviceRecord;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_item_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WorkItem workItem;
}
