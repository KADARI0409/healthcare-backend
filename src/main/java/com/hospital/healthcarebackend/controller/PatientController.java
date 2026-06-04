package com.hospital.healthcarebackend.controller;

import com.hospital.healthcarebackend.entity.Patient;
import com.hospital.healthcarebackend.entity.Doctor;
import com.hospital.healthcarebackend.repository.PatientRepository;
import com.hospital.healthcarebackend.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        if (patient.getDoctor() == null || patient.getDoctor().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Long doctorId = Objects.requireNonNull(patient.getDoctor().getId());
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return ResponseEntity.badRequest().body(null);
        }
        patient.setDoctor(doctor);
        Patient savedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        // return patientRepository.findAll();
        return patientRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @Valid @RequestBody Patient patientDetails) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient != null) {
            // Basic Information
            existingPatient.setPatientName(patientDetails.getPatientName());
            existingPatient.setAge(patientDetails.getAge());
            existingPatient.setSex(patientDetails.getSex());
            existingPatient.setVisitDate(patientDetails.getVisitDate());

            // Vital Signs
            existingPatient.setTemperature(patientDetails.getTemperature());
            existingPatient.setPr(patientDetails.getPr());
            existingPatient.setBpSystolic(patientDetails.getBpSystolic());
            existingPatient.setBpDiastolic(patientDetails.getBpDiastolic());
            existingPatient.setSpo2(patientDetails.getSpo2());

            // Clinical Examination Fields - ADD THESE
            existingPatient.setComplaints(patientDetails.getComplaints());
            existingPatient.setHistory(patientDetails.getHistory());
            existingPatient.setOnExamination(patientDetails.getOnExamination());
            existingPatient.setHeart(patientDetails.getHeart());
            existingPatient.setLungs(patientDetails.getLungs());
            existingPatient.setP_a(patientDetails.getP_a());
            existingPatient.setP_r(patientDetails.getP_r());

            // Doctor
            if (patientDetails.getDoctor() != null && patientDetails.getDoctor().getId() != null) {
                Long doctorId = Objects.requireNonNull(patientDetails.getDoctor().getId());
                Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
                existingPatient.setDoctor(doctor);
            }

            Patient savedPatient = patientRepository.save(existingPatient);
            return ResponseEntity.ok(savedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            patientRepository.delete(patient);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Patient> getPatientsByDoctor(@PathVariable Long doctorId) {
        return patientRepository.findByDoctorId(doctorId);
    }
}