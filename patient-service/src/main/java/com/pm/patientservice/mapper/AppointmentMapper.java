package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.AppointmentRequestDTO;
import com.pm.patientservice.dto.AppointmentResponseDTO;
import com.pm.patientservice.model.Appointment;
import com.pm.patientservice.model.Doctor;
import com.pm.patientservice.model.HealthRecords;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment toEntity(AppointmentRequestDTO dto, Patient patient, Doctor doctor, HealthRecords healthRecords) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setHealthRecords(healthRecords);
        appointment.setDetails(dto.getDetails());
        appointment.setStatus(Appointment.AppointmentStatus.valueOf(dto.getStatus()));
        appointment.setAppointmentStartDateTime(dto.getAppointmentStartDateTime());
        appointment.setAppointmentEndDateTime(dto.getAppointmentEndDateTime());
        appointment.setDurationMinutes(dto.getDurationMinutes());
        appointment.setLocation(dto.getLocation());
        return appointment;
    }

    public AppointmentResponseDTO toDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setPatientId(appointment.getPatient() != null ? appointment.getPatient().getId() : null);
        dto.setDoctorId(appointment.getDoctor() != null ? appointment.getDoctor().getId() : null);
        dto.setHealthRecordId(appointment.getHealthRecords() != null ? appointment.getHealthRecords().getHealthRecordId() : null);
        dto.setDetails(appointment.getDetails());
        dto.setStatus(appointment.getStatus());
        dto.setAppointmentStartDateTime(appointment.getAppointmentStartDateTime());
        dto.setAppointmentEndDateTime(appointment.getAppointmentEndDateTime());
        dto.setDurationMinutes(appointment.getDurationMinutes());
        dto.setLocation(appointment.getLocation());
        dto.setCreatedAt(appointment.getCreatedAt());
        dto.setUpdatedAt(appointment.getUpdatedAt());
        return dto;
    }
}