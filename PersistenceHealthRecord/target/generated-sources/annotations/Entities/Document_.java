package Entities;

import Entities.Expedient;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-16T16:55:46", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, Long> documentId;
    public static volatile SingularAttribute<Document, String> type;
    public static volatile SingularAttribute<Document, Expedient> expedient;
    public static volatile SingularAttribute<Document, String> nameDocument;
    public static volatile SingularAttribute<Document, String> contentMultimedia;

}