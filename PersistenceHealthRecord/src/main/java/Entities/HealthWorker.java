// HealthWorker.java
package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HealthWorker")
@DiscriminatorValue("HEALTH_WORKER")
public class HealthWorker extends User implements Serializable {

    @Column(name = "professional_license", unique = true, nullable = false)
    private String professionalLicense;

    public HealthWorker() {}

    public HealthWorker(String curp, String name, String lastName, String password, String biometricData, int age, String professionalLicense) {
        super.setCurp(curp);
        super.setName(name);
        super.setLastName(lastName);
        super.setPassword(password);
        super.setBiometricData(biometricData);
        super.setAge(age);
        this.professionalLicense = professionalLicense;
        super.setUserType("HEALTH_WORKER");
    }

    public String getProfessionalLicense() { return professionalLicense; }
    public void setProfessionalLicense(String professionalLicense) { this.professionalLicense = professionalLicense; }
}
