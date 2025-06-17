package com.techm.vsm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate serviceDate;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)  // make eager to fetch full vehicle info
    @JoinColumn(name = "vehicle_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "customer"})  // ignore customer if you want or handle separately
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_representative_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignore lazy props
    private ServiceRepresentative serviceRepresentative;

    @OneToMany(mappedBy = "serviceRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("serviceRecord")  // avoid infinite recursion
    private List<ServiceRecordItem> serviceItems;
}
