/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthrecord.entities;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "Patient")
@DiscriminatorValue("PATIENT")
public class Patient extends User implements Serializable {

    private static final long serialVersionUID = 1L;

      
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
    
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Expedient expedient;
    
    public Patient() {
    }
    
    public Patient(String curp, String name, String lastName, String password, String biometricData,
            int age, Tutor tutor, Expedient expedient) {
        super(curp, name, lastName, password, biometricData, age);
        this.tutor = tutor;
        this.expedient = expedient;
        if (expedient != null) {
            expedient.setPatient(this);
        }
    }
    
    // Constructor alternativo para evitar referencia circular
    public Patient(String curp, String name, String lastName, String password, String biometricData,
            int age, Tutor tutor) {
        super(curp, name, lastName, password, biometricData, age);
        this.tutor = tutor;
        this.expedient = null;
    }
    
    // Usamos el ID heredado de User
    public long getPatientId() {
        return getUserId();
    }
    
    public void setPatientId(long patientId) {
        setUserId(patientId);
    }
    
    public Tutor getTutor() {
        return tutor;
    }
    
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    
    public Expedient getExpedient() {
        return expedient;
    }
    
    public void setExpedient(Expedient expedient) {
        this.expedient = expedient;
        if (expedient != null) {
            expedient.setPatient(this);
        }
    }
    
    @Override
    public String toString() {
        return "Patient{" + "patientId=" + getUserId() + 
               ", curp=" + getCurp() + 
               ", name=" + getName() + 
               ", lastName=" + getLastName() + 
               ", age=" + getAge() + 
               ", tutor=" + (tutor != null ? "Tutor(id=" + tutor.getTutorId() + ")" : "null") + 
               '}';
    }
}
