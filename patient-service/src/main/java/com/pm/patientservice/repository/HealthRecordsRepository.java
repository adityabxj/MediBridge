package com.pm.patientservice.repository;

import com.pm.patientservice.model.HealthRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HealthRecordsRepository extends JpaRepository<HealthRecords, UUID> {
}