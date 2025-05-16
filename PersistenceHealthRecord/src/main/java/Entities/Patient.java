
package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Patient")
@DiscriminatorValue("PATIENT")
public class Patient extends User implements Serializable {

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Expedient expedient;

    public Patient() {}

    public Patient(String curp, String name, String lastName, String password, String biometricData, int age, Tutor tutor, Expedient expedient) {
        super.setCurp(curp);
        super.setName(name);
        super.setLastName(lastName);
        super.setPassword(password);
        super.setBiometricData(biometricData);
        super.setAge(age);
        this.tutor = tutor;
        this.expedient = expedient;
        super.setUserType("PATIENT");
        if (expedient != null) {
            expedient.setPatient(this);
        }
    }

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    public Expedient getExpedient() { return expedient; }
    public void setExpedient(Expedient expedient) {
        this.expedient = expedient;
        if (expedient != null) {
            expedient.setPatient(this);
        }
    }
}
