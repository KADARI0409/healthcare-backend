package com.hospital.healthcarebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    @Column(nullable = false)
    private String patientName;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be 0 or greater")
    @Max(value = 150, message = "Age must be less than 150")
    private Integer age;

    @NotBlank(message = "Sex is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Sex must be Male, Female, or Other")
    private String sex;

    @NotNull(message = "Visit date is required")
    @PastOrPresent(message = "Visit date cannot be in the future")
    private LocalDate visitDate;

    // New Vital Signs Fields
    @DecimalMin(value = "95.0", message = "Temperature must be at least 95.0°F")
    @DecimalMax(value = "104.0", message = "Temperature must not exceed 104.0°F")
    private Double temperature;

    @Min(value = 40, message = "Pulse rate must be at least 40 bpm")
    @Max(value = 200, message = "Pulse rate must not exceed 200 bpm")
    private Integer pr; // Pulse Rate

    @Min(value = 60, message = "Systolic BP must be at least 60 mmHg")
    @Max(value = 200, message = "Systolic BP must not exceed 200 mmHg")
    private Integer bpSystolic; // Systolic BP (top number)

    @Min(value = 40, message = "Diastolic BP must be at least 40 mmHg")
    @Max(value = 130, message = "Diastolic BP must not exceed 130 mmHg")
    private Integer bpDiastolic; // Diastolic BP (bottom number)

    @Min(value = 70, message = "SpO2 must be at least 70%")
    @Max(value = 100, message = "SpO2 must not exceed 100%")
    private Integer spo2;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // Clinical Examination Fields
    @Column(columnDefinition = "TEXT")
    private String complaints; // Chief complaints

    @Column(columnDefinition = "TEXT")
    private String history; // History of present illness

    @Column(columnDefinition = "TEXT")
    private String onExamination; // General examination findings

    private String heart;
    private String lungs;
    private String p_a;
    private String p_r;

    // Default constructor
    public Patient() {
    }

    // Constructor with all fields
    public Patient(String patientName, Integer age, String sex, LocalDate visitDate,
            Double temperature, Integer pr, Integer bpSystolic, Integer bpDiastolic,
            Integer spo2, Doctor doctor) {
        this.patientName = patientName;
        this.age = age;
        this.sex = sex;
        this.visitDate = visitDate;
        this.temperature = temperature;
        this.pr = pr;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.spo2 = spo2;
        this.doctor = doctor;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPr() {
        return pr;
    }

    public void setPr(Integer pr) {
        this.pr = pr;
    }

    public Integer getBpSystolic() {
        return bpSystolic;
    }

    public void setBpSystolic(Integer bpSystolic) {
        this.bpSystolic = bpSystolic;
    }

    public Integer getBpDiastolic() {
        return bpDiastolic;
    }

    public void setBpDiastolic(Integer bpDiastolic) {
        this.bpDiastolic = bpDiastolic;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // private String heart; // Heart examination
    // private String lungs; // Lungs examination
    // private String p_a; // Per Abdomen examination
    // private String p_r; // Per Rectum examination

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getOnExamination() {
        return onExamination;
    }

    public void setOnExamination(String onExamination) {
        this.onExamination = onExamination;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getLungs() {
        return lungs;
    }

    public void setLungs(String lungs) {
        this.lungs = lungs;
    }

    public String getP_a() {
        return p_a;
    }

    public void setP_a(String p_a) {
        this.p_a = p_a;
    }

    public String getP_r() {
        return p_r;
    }

    public void setP_r(String p_r) {
        this.p_r = p_r;
    }
}