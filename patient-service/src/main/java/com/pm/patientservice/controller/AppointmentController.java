package com.pm.patientservice.controller;

import com.pm.patientservice.dto.AppointmentRequestDTO;
import com.pm.patientservice.dto.AppointmentResponseDTO;
import com.pm.patientservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO) {
        AppointmentResponseDTO responseDTO = appointmentService.createAppointment(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<AppointmentResponseDTO> getAppointment(@RequestParam("appointmentId") UUID id) {
        AppointmentResponseDTO responseDTO = appointmentService.getAppointment(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        List<AppointmentResponseDTO> responseDTOs = appointmentService.getAllAppointments();
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@RequestParam("appointmentId") UUID id,
                                                                    @Valid @RequestBody AppointmentRequestDTO requestDTO) {
        AppointmentResponseDTO responseDTO = appointmentService.updateAppointment(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAppointment(@RequestParam("appointmentId") UUID id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}