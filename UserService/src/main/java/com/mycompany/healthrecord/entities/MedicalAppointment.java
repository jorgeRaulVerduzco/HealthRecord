/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthrecord.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "MedicalAppointment")
public class MedicalAppointment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_appointment_id")
    private long medicalAppointmentId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private HealthWorker healthWorker;
    
    @ManyToOne
    @JoinColumn(name = "patient_user_id")
    private Patient patient;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Date date;
    
    @Column(length = 20)
    private String status;
    
    @Column(length = 1000)
    private String description;

    public MedicalAppointment() {
    }

    public MedicalAppointment(HealthWorker healthWorker, Patient patient, Date date, String status, String description) {
        this.healthWorker = healthWorker;
        this.patient = patient;
        this.date = date;
        this.status = status;
        this.description = description;
    }

    public long getMedicalAppointmentId() {
        return medicalAppointmentId;
    }

    public void setMedicalAppointmentId(long medicalAppointmentId) {
        this.medicalAppointmentId = medicalAppointmentId;
    }

    public HealthWorker getHealthWorker() {
        return healthWorker;
    }

    public void setHealthWorker(HealthWorker healthWorker) {
        this.healthWorker = healthWorker;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MedicalAppointment{" + "medicalAppointmentId=" + medicalAppointmentId + ", healthWorker=" + healthWorker + ", patient=" + patient + ", date=" + date + ", status=" + status + ", description=" + description + '}';
    }
   
}
