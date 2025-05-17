package Entities;

import Entities.Document;
import Entities.Patient;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-16T16:55:46", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Expedient.class)
public class Expedient_ { 

    public static volatile SingularAttribute<Expedient, Date> date;
    public static volatile SingularAttribute<Expedient, Boolean> authorization;
    public static volatile SingularAttribute<Expedient, Long> expedientId;
    public static volatile ListAttribute<Expedient, Document> documents;
    public static volatile SingularAttribute<Expedient, Patient> patient;

}