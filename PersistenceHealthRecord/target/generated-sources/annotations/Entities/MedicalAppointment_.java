package Entities;

import Entities.HealthWorker;
import Entities.Patient;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-11T23:25:23", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(MedicalAppointment.class)
public class MedicalAppointment_ { 

    public static volatile SingularAttribute<MedicalAppointment, Date> date;
    public static volatile SingularAttribute<MedicalAppointment, Patient> patient;
    public static volatile SingularAttribute<MedicalAppointment, String> description;
    public static volatile SingularAttribute<MedicalAppointment, HealthWorker> healthWorker;
    public static volatile SingularAttribute<MedicalAppointment, Long> medicalAppointmentId;
    public static volatile SingularAttribute<MedicalAppointment, String> status;

}