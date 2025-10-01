package com.citiustech.MediGenie.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private User patient;

    @Column(nullable = false)
    private LocalDate date;

    private Double weight;

    private Double bloodPressure;

    private String symptoms;
}
