package com.pm.patientservice.service;

import com.pm.patientservice.dto.PackagesRequestDTO;
import com.pm.patientservice.dto.PackagesResponseDTO;
import com.pm.patientservice.mapper.PackagesMapper;
import com.pm.patientservice.model.Doctor;
import com.pm.patientservice.model.Packages;
import com.pm.patientservice.repository.DoctorRepository;
import com.pm.patientservice.repository.PackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PackageService {

    private final PackagesRepository packagesRepository;
    private final DoctorRepository doctorRepository;
    private final PackagesMapper packagesMapper;

    @Autowired
    public PackageService(PackagesRepository packagesRepository,
                           DoctorRepository doctorRepository,
                           PackagesMapper packagesMapper) {
        this.packagesRepository = packagesRepository;
        this.doctorRepository = doctorRepository;
        this.packagesMapper = packagesMapper;
    }

    @Transactional
    public PackagesResponseDTO createPackages(PackagesRequestDTO requestDTO) {
        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Packages packages = packagesMapper.toEntity(requestDTO, doctor);
        Packages savedPackages = packagesRepository.save(packages);
        return packagesMapper.toDTO(savedPackages);
    }

    @Transactional(readOnly = true)
    public PackagesResponseDTO getPackages(UUID id) {
        Packages packages = packagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        return packagesMapper.toDTO(packages);
    }

    @Transactional(readOnly = true)
    public List<PackagesResponseDTO> getAllPackages() {
        return packagesRepository.findAll().stream()
                .map(packagesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PackagesResponseDTO updatePackages(UUID id, PackagesRequestDTO requestDTO) {
        Packages packages = packagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        packages.setName(requestDTO.getName());
        packages.setPrice(requestDTO.getPrice());
        packages.setDetails(requestDTO.getDetails());
        packages.setCity(requestDTO.getCity());
        packages.setDoctor(doctor);

        Packages updatedPackages = packagesRepository.save(packages);
        return packagesMapper.toDTO(updatedPackages);
    }

    @Transactional
    public void deletePackages(UUID id) {
        Packages packages = packagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        packagesRepository.delete(packages);
    }
}