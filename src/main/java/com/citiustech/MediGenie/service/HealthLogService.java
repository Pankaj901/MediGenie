package com.citiustech.MediGenie.service;


import com.citiustech.MediGenie.model.HealthLog;
import com.citiustech.MediGenie.repository.HealthLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthLogService {
    @Autowired
    private HealthLogRepository healthLogRepository;

    public HealthLog save(HealthLog healthLog) {
        return healthLogRepository.save(healthLog);
    }

    public List<HealthLog> findByPatientId(Long patientId) {
        return healthLogRepository.findByPatientIdOrderByDateDesc(patientId);
    }
}

