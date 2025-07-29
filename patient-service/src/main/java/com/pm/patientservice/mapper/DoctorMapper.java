package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.DoctorRequestDTO;
import com.pm.patientservice.dto.DoctorResponseDTO;
import com.pm.patientservice.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setGender(dto.getGender());
        doctor.setEmail(dto.getEmail());
        doctor.setMobileNumber(dto.getMobileNumber());
        doctor.setStatus(dto.getStatus() != null ? Doctor.DoctorStatus.valueOf(dto.getStatus()) : null);
        doctor.setDetails(dto.getDetails());
        doctor.setLocation(dto.getLocation());
        doctor.setAvailableFrom(dto.getAvailableFrom());
        doctor.setAvailableUpto(dto.getAvailableUpto());
        doctor.setExperience(dto.getExperience());
        doctor.setRating(dto.getRating());
        doctor.setServicesProvided(dto.getServicesProvided());
        return doctor;
    }

    public DoctorResponseDTO toDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setGender(doctor.getGender());
        dto.setEmail(doctor.getEmail());
        dto.setMobileNumber(doctor.getMobileNumber());
        dto.setStatus(doctor.getStatus());
        dto.setDetails(doctor.getDetails());
        dto.setLocation(doctor.getLocation());
        dto.setAvailableFrom(doctor.getAvailableFrom());
        dto.setAvailableUpto(doctor.getAvailableUpto());
        dto.setExperience(doctor.getExperience());
        dto.setRating(doctor.getRating());
        dto.setServicesProvided(doctor.getServicesProvided());
        dto.setCreatedAt(doctor.getCreatedAt());
        dto.setUpdatedAt(doctor.getUpdatedAt());
        return dto;
    }
}