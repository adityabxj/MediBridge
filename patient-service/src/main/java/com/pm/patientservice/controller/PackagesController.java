package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PackagesRequestDTO;
import com.pm.patientservice.dto.PackagesResponseDTO;
import com.pm.patientservice.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/packages")
public class PackagesController {

    private final PackageService packagesService;

    @Autowired
    public PackagesController(PackageService packagesService) {
        this.packagesService = packagesService;
    }

    @PostMapping
    public ResponseEntity<PackagesResponseDTO> createPackages(@Valid @RequestBody PackagesRequestDTO requestDTO) {
        PackagesResponseDTO responseDTO = packagesService.createPackages(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<PackagesResponseDTO> getPackages(@RequestParam("packageId") UUID id) {
        PackagesResponseDTO responseDTO = packagesService.getPackages(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PackagesResponseDTO>> getAllPackages() {
        List<PackagesResponseDTO> responseDTOs = packagesService.getAllPackages();
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/update")
    public ResponseEntity<PackagesResponseDTO> updatePackages(@RequestParam("packageId") UUID id,
                                                              @Valid @RequestBody PackagesRequestDTO requestDTO) {
        PackagesResponseDTO responseDTO = packagesService.updatePackages(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePackages(@RequestParam("packageId") UUID id) {
        packagesService.deletePackages(id);
        return ResponseEntity.noContent().build();
    }
}