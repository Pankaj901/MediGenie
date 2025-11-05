package com.citiustech.MediGenie.service;

import com.citiustech.MediGenie.model.Appointment;
import com.citiustech.MediGenie.repository.AppointmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReminderService {

    private final GenAIService genAIService;
    private final AppointmentRepository appointmentRepo;
    private final EmailService emailService;

    public ReminderService(GenAIService genAIService, AppointmentRepository appointmentRepo, EmailService emailService) {
        this.genAIService = genAIService;
        this.appointmentRepo = appointmentRepo;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 * * * *") // Every hour
    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in24Hours = now.plusHours(24);
        List<Appointment> upcoming = appointmentRepo.findByAppointmentTimeBetween(now, in24Hours);

        for (Appointment appt : upcoming) {
            String prompt = "Compose a friendly appointment reminder for "
                    + appt.getPatient().getFullName()
                    + " for an appointment with Dr. " + appt.getDoctor().getFullName()
                    + " at " + appt.getAppointmentTime() + ".";
            String aiReminder = genAIService.getAIResponse(prompt);

            // Send email to patient
            String subject = "MediGenie: Appointment Reminder";
            String recipient = appt.getPatient().getEmail();
            emailService.sendEmail(recipient, subject, aiReminder);

            // Optionally log for debug
            System.out.println("Sent reminder to " + recipient + ": " + aiReminder);
        }
    }
}
