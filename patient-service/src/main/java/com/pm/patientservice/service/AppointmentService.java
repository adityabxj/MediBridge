package com.pm.patientservice.service;

import com.pm.patientservice.dto.AppointmentRequestDTO;
import com.pm.patientservice.dto.AppointmentResponseDTO;
import com.pm.patientservice.mapper.AppointmentMapper;
import com.pm.patientservice.model.Appointment;
import com.pm.patientservice.model.Doctor;
import com.pm.patientservice.model.HealthRecords;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.AppointmentRepository;
import com.pm.patientservice.repository.DoctorRepository;
import com.pm.patientservice.repository.HealthRecordsRepository;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HealthRecordsRepository healthRecordsRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository,
                              HealthRecordsRepository healthRecordsRepository,
                              AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.healthRecordsRepository = healthRecordsRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {
        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        HealthRecords healthRecords = null;
        if (requestDTO.getHealthRecordId() != null) {
            healthRecords = healthRecordsRepository.findById(requestDTO.getHealthRecordId())
                    .orElseThrow(() -> new RuntimeException("Health Record not found"));
        }

        Appointment appointment = appointmentMapper.toEntity(requestDTO, patient, doctor, healthRecords);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(savedAppointment);
    }

    @Transactional(readOnly = true)
    public AppointmentResponseDTO getAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toDTO(appointment);
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AppointmentResponseDTO updateAppointment(UUID id, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        HealthRecords healthRecords = null;
        if (requestDTO.getHealthRecordId() != null) {
            healthRecords = healthRecordsRepository.findById(requestDTO.getHealthRecordId())
                    .orElseThrow(() -> new RuntimeException("Health Record not found"));
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setHealthRecords(healthRecords);
        appointment.setDetails(requestDTO.getDetails());
        appointment.setStatus(Appointment.AppointmentStatus.valueOf(requestDTO.getStatus()));
        appointment.setAppointmentStartDateTime(requestDTO.getAppointmentStartDateTime());
        appointment.setAppointmentEndDateTime(requestDTO.getAppointmentEndDateTime());
        appointment.setDurationMinutes(requestDTO.getDurationMinutes());
        appointment.setLocation(requestDTO.getLocation());

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(updatedAppointment);
    }

    @Transactional
    public void deleteAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }
}