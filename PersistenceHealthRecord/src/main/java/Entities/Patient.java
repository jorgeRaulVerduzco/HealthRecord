/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "Patient")
@DiscriminatorValue("PATIENT")
public class Patient extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    // No se necesita un ID separado, se usa el heredado de User
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToOne(mappedBy = "patient")
    private Expedient expedient;

    public Patient() {
    }

    public Patient(String curp, String name, String lastName, String password, String biometricData,
            int age, Tutor tutor, Expedient expedient) {
        super(curp, name, lastName, password, biometricData, age);
        this.tutor = tutor;
        this.expedient = expedient;
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
    }

    @Override
    public String toString() {
        return "Patient{" + "patientId=" + getUserId() + ", tutor=" + tutor + ", expedient=" + expedient + '}';
    }
}
