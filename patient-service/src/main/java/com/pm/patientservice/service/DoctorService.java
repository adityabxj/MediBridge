package com.pm.patientservice.service;

import com.pm.patientservice.dto.DoctorRequestDTO;
import com.pm.patientservice.dto.DoctorResponseDTO;
import com.pm.patientservice.mapper.DoctorMapper;
import com.pm.patientservice.model.Doctor;
import com.pm.patientservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorMapper.toEntity(requestDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
    }

    @Transactional(readOnly = true)
    public DoctorResponseDTO getDoctor(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctorMapper.toDTO(doctor);
    }

    @Transactional(readOnly = true)
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DoctorResponseDTO updateDoctor(UUID id, DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(requestDTO.getName());
        doctor.setGender(requestDTO.getGender());
        doctor.setEmail(requestDTO.getEmail());
        doctor.setMobileNumber(requestDTO.getMobileNumber());
        doctor.setStatus(requestDTO.getStatus() != null ? Doctor.DoctorStatus.valueOf(requestDTO.getStatus()) : null);
        doctor.setDetails(requestDTO.getDetails());
        doctor.setLocation(requestDTO.getLocation());
        doctor.setAvailableFrom(requestDTO.getAvailableFrom());
        doctor.setAvailableUpto(requestDTO.getAvailableUpto());
        doctor.setExperience(requestDTO.getExperience());
        doctor.setRating(requestDTO.getRating());
        doctor.setServicesProvided(requestDTO.getServicesProvided());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(updatedDoctor);
    }

    @Transactional
    public String deleteDoctor(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorRepository.delete(doctor);
        return "Doctor with ID " + id + " deleted successfully";
    }
}