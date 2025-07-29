package com.pm.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentRequestDTO {
    @NotNull(message = "Patient ID cannot be null")
    private UUID patientId;

    @NotNull(message = "Doctor ID cannot be null")
    private UUID doctorId;

    private UUID healthRecordId;

    @NotBlank(message = "Details cannot be empty")
    private String details;

    @NotNull(message = "Status cannot be null")
    private String status;

    @NotNull(message = "Start date-time cannot be null")
    private LocalDateTime appointmentStartDateTime;

    @NotNull(message = "End date-time cannot be null")
    private LocalDateTime appointmentEndDateTime;

    @NotNull(message = "Duration cannot be null")
    private Integer durationMinutes;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    // Getters and Setters
    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public UUID getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(UUID healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }

    public void setAppointmentStartDateTime(LocalDateTime appointmentStartDateTime) {
        this.appointmentStartDateTime = appointmentStartDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public void setAppointmentEndDateTime(LocalDateTime appointmentEndDateTime) {
        this.appointmentEndDateTime = appointmentEndDateTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}