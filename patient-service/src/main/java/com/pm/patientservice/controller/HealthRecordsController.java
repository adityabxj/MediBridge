package com.pm.patientservice.controller;

import com.pm.patientservice.dto.HealthRecordsRequestDTO;
import com.pm.patientservice.dto.HealthRecordsResponseDTO;
import com.pm.patientservice.service.HealthRecordsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/healthRecord")
public class HealthRecordsController {

    private final HealthRecordsService healthRecordsService;

    @Autowired
    public HealthRecordsController(HealthRecordsService healthRecordsService) {
        this.healthRecordsService = healthRecordsService;
    }

    @PostMapping
    public ResponseEntity<HealthRecordsResponseDTO> createHealthRecords(@Valid @RequestBody HealthRecordsRequestDTO requestDTO) {
        HealthRecordsResponseDTO responseDTO = healthRecordsService.createHealthRecords(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<HealthRecordsResponseDTO> getHealthRecords(@RequestParam("healthRecordId") UUID id) {
        HealthRecordsResponseDTO responseDTO = healthRecordsService.getHealthRecords(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<HealthRecordsResponseDTO>> getAllHealthRecords() {
        List<HealthRecordsResponseDTO> responseDTOs = healthRecordsService.getAllHealthRecords();
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/update")
    public ResponseEntity<HealthRecordsResponseDTO> updateHealthRecords(@RequestParam("healthRecordId") UUID id,
                                                                        @Valid @RequestBody HealthRecordsRequestDTO requestDTO) {
        HealthRecordsResponseDTO responseDTO = healthRecordsService.updateHealthRecords(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteHealthRecords(@RequestParam("healthRecordId") UUID id) {
        healthRecordsService.deleteHealthRecords(id);
        return ResponseEntity.noContent().build();
    }
}