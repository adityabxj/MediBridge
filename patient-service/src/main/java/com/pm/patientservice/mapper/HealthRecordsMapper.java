package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.HealthRecordsRequestDTO;
import com.pm.patientservice.dto.HealthRecordsResponseDTO;
import com.pm.patientservice.model.HealthRecords;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class HealthRecordsMapper {

    public HealthRecords toEntity(HealthRecordsRequestDTO dto, Patient patient) {
        HealthRecords healthRecords = new HealthRecords();
        healthRecords.setPatient(patient);
        healthRecords.setSymptoms(dto.getSymptoms());
        healthRecords.setDiagnosis(dto.getDiagnosis());
        healthRecords.setPrescription(dto.getPrescription());
        return healthRecords;
    }

    public HealthRecordsResponseDTO toDTO(HealthRecords healthRecords) {
        HealthRecordsResponseDTO dto = new HealthRecordsResponseDTO();
        dto.setHealthRecordId(healthRecords.getHealthRecordId());
        dto.setPatientId(healthRecords.getPatient() != null ? healthRecords.getPatient().getId() : null);
        dto.setSymptoms(healthRecords.getSymptoms());
        dto.setDiagnosis(healthRecords.getDiagnosis());
        dto.setPrescription(healthRecords.getPrescription());
        dto.setCreatedAt(healthRecords.getCreatedAt());
        dto.setUpdatedAt(healthRecords.getUpdatedAt());
        return dto;
    }
}