package com.pm.patientservice.service;

import com.pm.patientservice.dto.HealthRecordsRequestDTO;
import com.pm.patientservice.dto.HealthRecordsResponseDTO;
import com.pm.patientservice.mapper.HealthRecordsMapper;
import com.pm.patientservice.model.HealthRecords;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.HealthRecordsRepository;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HealthRecordsService {

    private final HealthRecordsRepository healthRecordsRepository;
    private final PatientRepository patientRepository;
    private final HealthRecordsMapper healthRecordsMapper;

    @Autowired
    public HealthRecordsService(HealthRecordsRepository healthRecordsRepository,
                                PatientRepository patientRepository,
                                HealthRecordsMapper healthRecordsMapper) {
        this.healthRecordsRepository = healthRecordsRepository;
        this.patientRepository = patientRepository;
        this.healthRecordsMapper = healthRecordsMapper;
    }

    @Transactional
    public HealthRecordsResponseDTO createHealthRecords(HealthRecordsRequestDTO requestDTO) {
        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        HealthRecords healthRecords = healthRecordsMapper.toEntity(requestDTO, patient);
        HealthRecords savedHealthRecords = healthRecordsRepository.save(healthRecords);
        return healthRecordsMapper.toDTO(savedHealthRecords);
    }

    @Transactional(readOnly = true)
    public HealthRecordsResponseDTO getHealthRecords(UUID id) {
        HealthRecords healthRecords = healthRecordsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health Record not found"));
        return healthRecordsMapper.toDTO(healthRecords);
    }

    @Transactional(readOnly = true)
    public List<HealthRecordsResponseDTO> getAllHealthRecords() {
        return healthRecordsRepository.findAll().stream()
                .map(healthRecordsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public HealthRecordsResponseDTO updateHealthRecords(UUID id, HealthRecordsRequestDTO requestDTO) {
        HealthRecords healthRecords = healthRecordsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health Record not found"));
        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        healthRecords.setPatient(patient);
        healthRecords.setSymptoms(requestDTO.getSymptoms());
        healthRecords.setDiagnosis(requestDTO.getDiagnosis());
        healthRecords.setPrescription(requestDTO.getPrescription());

        HealthRecords updatedHealthRecords = healthRecordsRepository.save(healthRecords);
        return healthRecordsMapper.toDTO(updatedHealthRecords);
    }

    @Transactional
    public void deleteHealthRecords(UUID id) {
        HealthRecords healthRecords = healthRecordsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health Record not found"));
        healthRecordsRepository.delete(healthRecords);
    }
}