package com.pm.patientservice.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class DoctorRequestDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile number cannot be empty")
    private String mobileNumber;

    @NotNull(message = "Status cannot be null")
    private String status;

    private String details;

    private String location;

    private LocalDateTime availableFrom;

    private LocalDateTime availableUpto;

    @PositiveOrZero(message = "Experience must be zero or positive")
    private Integer experience;

    @PositiveOrZero(message = "Rating must be zero or positive")
    private Double rating;

    private List<String> servicesProvided;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDateTime getAvailableUpto() {
        return availableUpto;
    }

    public void setAvailableUpto(LocalDateTime availableUpto) {
        this.availableUpto = availableUpto;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(List<String> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }
}