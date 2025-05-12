package Entities;

import Entities.Expedient;
import Entities.Tutor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-11T23:25:23", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Patient.class)
public class Patient_ extends User_ {

    public static volatile SingularAttribute<Patient, Expedient> expedient;
    public static volatile SingularAttribute<Patient, Tutor> tutor;

}