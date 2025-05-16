// Tutor.java
package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Tutor")
@DiscriminatorValue("TUTOR")
public class Tutor extends User implements Serializable {

    @OneToMany(mappedBy = "tutor")
    private List<Patient> patients;

    public Tutor() {}

    public Tutor(String curp, String name, String lastName, String password, String biometricData, int age, List<Patient> patients) {
        super.setCurp(curp);
        super.setName(name);
        super.setLastName(lastName);
        super.setPassword(password);
        super.setBiometricData(biometricData);
        super.setAge(age);
        this.patients = patients;
        super.setUserType("TUTOR");
    }

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }
}
