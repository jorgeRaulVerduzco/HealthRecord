package Entities;

import Entities.Patient;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-16T17:18:29", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tutor.class)
public class Tutor_ extends User_ {

    public static volatile ListAttribute<Tutor, Patient> patients;

}