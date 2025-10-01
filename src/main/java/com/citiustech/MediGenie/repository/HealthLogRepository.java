package com.citiustech.MediGenie.repository;

import com.citiustech.MediGenie.model.HealthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {
    List<HealthLog> findByPatientIdOrderByDateDesc(Long patientId);
}

