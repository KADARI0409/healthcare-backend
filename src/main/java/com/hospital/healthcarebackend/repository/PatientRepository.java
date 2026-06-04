package com.hospital.healthcarebackend.repository;

import com.hospital.healthcarebackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByDoctorId(Long doctorId);

    @Query("SELECT p FROM Patient p ORDER BY p.id DESC")
    List<Patient> findAllByOrderByIdDesc();
}