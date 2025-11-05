package com.citiustech.MediGenie.controller;

import com.citiustech.MediGenie.model.HealthLog;
import com.citiustech.MediGenie.service.HealthLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/healthlogs")
@RequiredArgsConstructor
public class HealthLogController {
    @Autowired
    private HealthLogService healthLogService;

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping
    public ResponseEntity<HealthLog> createHealthLog(@RequestBody HealthLog healthLog) {
        HealthLog saved = healthLogService.save(healthLog);
        return ResponseEntity.ok(saved);
    }
    @PreAuthorize("hasAnyRole('PATIENT','DOCTOR','ADMIN')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<HealthLog>> getHealthLogsByPatient(@PathVariable Long patientId) {
        List<HealthLog> logs = healthLogService.findByPatientId(patientId);
        return ResponseEntity.ok(logs);
    }
}

