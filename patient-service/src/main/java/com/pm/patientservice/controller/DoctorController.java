package com.pm.patientservice.controller;

import com.pm.patientservice.dto.DoctorRequestDTO;
import com.pm.patientservice.dto.DoctorResponseDTO;
import com.pm.patientservice.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO requestDTO) {
        DoctorResponseDTO responseDTO = doctorService.createDoctor(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<DoctorResponseDTO> getDoctor(@RequestParam("doctorId") UUID id) {
        DoctorResponseDTO responseDTO = doctorService.getDoctor(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        List<DoctorResponseDTO> responseDTOs = doctorService.getAllDoctors();
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/update")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@RequestParam("doctorId") UUID id,
                                                          @Valid @RequestBody DoctorRequestDTO requestDTO) {
        DoctorResponseDTO responseDTO = doctorService.updateDoctor(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDoctor(@RequestParam("doctorId") UUID id) {
        String responseMessage = doctorService.deleteDoctor(id);
        return ResponseEntity.ok().body(responseMessage);
    }
}