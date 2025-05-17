package Entities;

import Entities.Expedient;
import Entities.Tutor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-16T16:55:46", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Patient.class)
public class Patient_ extends User_ {

    public static volatile SingularAttribute<Patient, Expedient> expedient;
    public static volatile SingularAttribute<Patient, Tutor> tutor;

}