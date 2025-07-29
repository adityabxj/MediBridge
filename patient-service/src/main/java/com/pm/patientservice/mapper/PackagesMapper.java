package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PackagesRequestDTO;
import com.pm.patientservice.dto.PackagesResponseDTO;
import com.pm.patientservice.model.Doctor;
import com.pm.patientservice.model.Packages;
import org.springframework.stereotype.Component;

@Component
public class PackagesMapper {

    public Packages toEntity(PackagesRequestDTO dto, Doctor doctor) {
        Packages packages = new Packages();
        packages.setName(dto.getName());
        packages.setPrice(dto.getPrice());
        packages.setDetails(dto.getDetails());
        packages.setCity(dto.getCity());
        packages.setDoctor(doctor);
        return packages;
    }

    public PackagesResponseDTO toDTO(Packages packages) {
        PackagesResponseDTO dto = new PackagesResponseDTO();
        dto.setPackageId(packages.getPackageId());
        dto.setName(packages.getName());
        dto.setPrice(packages.getPrice());
        dto.setDetails(packages.getDetails());
        dto.setCity(packages.getCity());
        dto.setDoctorId(packages.getDoctor() != null ? packages.getDoctor().getId() : null);
        dto.setCreatedAt(packages.getCreatedAt());
        dto.setUpdatedAt(packages.getUpdatedAt());
        return dto;
    }
}