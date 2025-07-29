package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO responseDTO = patientService.createPatient(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<PatientResponseDTO> getPatient(@RequestParam("patientId") UUID id) {
        PatientResponseDTO responseDTO = patientService.getPatient(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> responseDTOs = patientService.getAllPatients();
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientResponseDTO> updatePatient(@RequestParam("patientId") UUID id,
                                                            @Valid @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO responseDTO = patientService.updatePatient(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePatient(@RequestParam("patientId") UUID id) {
        String responseMessage = patientService.deletePatient(id);
        return ResponseEntity.ok().body(responseMessage);
    }
}