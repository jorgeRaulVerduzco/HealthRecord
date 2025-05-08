/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "Tutor")
@DiscriminatorValue("TUTOR")
public class Tutor extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    // No se necesita un ID separado, se usa el heredado de User
    
    // private String curp;
    // private int age;
    
    @OneToMany(mappedBy = "tutor")
    private List<Patient> patients;

    public Tutor() {
    }

    public Tutor(String curp, String name, String lastName, String password, String biometricData,
            int age, List<Patient> patients) {
        super(curp, name, lastName, password, biometricData, age);
        this.patients = patients;
    }

    // Usamos el ID heredado de User
    public long getTutorId() {
        return getUserId();
    }

    public void setTutorId(long tutorId) {
        setUserId(tutorId);
    }

    public String getCurp() {
        return super.getCurp();
    }
    
    public void setCurp(String curp) {
        super.setCurp(curp);
    }

    public int getAge() {
        return super.getAge();
    }

    public void setAge(int age) {
        super.setAge(age);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Tutor{" + "tutorId=" + getUserId() + ", curp=" + getCurp() + ", age=" + getAge() + ", patients=" + patients + '}';
    }
}
