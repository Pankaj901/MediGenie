package com.citiustech.MediGenie.controller;


import com.citiustech.MediGenie.model.Appointment;
import com.citiustech.MediGenie.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    // Patients (and Admins) create appointments
    @PreAuthorize("hasAnyRole('PATIENT','ADMIN')")
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment saved = appointmentService.save(appointment);
        return ResponseEntity.ok(saved);
    }
    // Patient/Doctor/Admin fetch relevant appointments
    @PreAuthorize("hasAnyRole('PATIENT','DOCTOR','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.findById(id);
        return appointment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    // Doctor/Admin fetch appointment for a patientId
    @PreAuthorize("hasAnyRole('DOCTOR','ADMIN')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<Appointment> list = appointmentService.findByPatientId(patientId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> list = appointmentService.findByDoctorId(doctorId);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
