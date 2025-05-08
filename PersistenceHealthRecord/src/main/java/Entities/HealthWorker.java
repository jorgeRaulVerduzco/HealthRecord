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
import javax.persistence.Table;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "HealthWorker")
@DiscriminatorValue("HEALTH_WORKER")
public class HealthWorker extends User implements Serializable{
    private static final long serialVersionUID = 1L;

    // No se necesita un ID separado, se usa el heredado de User
    
    @Column(name = "professional_license", unique = true, nullable = false)
    private String professionalLicense;

    public HealthWorker() {
    }

    public HealthWorker(String curp, String name, String lastName, String password, String biometricData,
            int age, String professionalLicense) {
        super(curp, name, lastName, password, biometricData, age);
        this.professionalLicense = professionalLicense;
    }

    // Usamos el ID heredado de User
    public long getHealthWorkerId() {
        return getUserId();
    }

    public void setHealthWorkerId(long healthWorkerId) {
        setUserId(healthWorkerId);
    }

    public String getProfessionalLicense() {
        return professionalLicense;
    }

    public void setProfessionalLicense(String professionalLicense) {
        this.professionalLicense = professionalLicense;
    }
    
    @Override
    public String toString() {
        return "HealthWorker{" + "healthWorkerId=" + getUserId() + ", professionalLicense=" + professionalLicense + ", " + super.toString() + '}';
    }
}
