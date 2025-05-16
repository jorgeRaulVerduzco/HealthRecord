// Expedient.java
package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Expedient")
public class Expedient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expedient_id")
    private long expedientId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date date;

    @OneToMany(mappedBy = "expedient", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();

    @Column(name = "authorization")
    private boolean authorization;

    public Expedient() {}

    public Expedient(Patient patient, Date date, boolean authorization) {
        this.patient = patient;
        this.date = date;
        this.authorization = authorization;
    }

    public Expedient(Patient patient, Date date, List<Document> documents, boolean authorization) {
        this.patient = patient;
        this.date = date;
        this.authorization = authorization;
        if (documents != null) {
            this.documents.addAll(documents);
            for (Document doc : documents) {
                doc.setExpedient(this);
            }
        }
    }

    public long getExpedientId() {
        return expedientId;
    }

    public void setExpedientId(long expedientId) {
        this.expedientId = expedientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
        if (documents != null) {
            for (Document document : documents) {
                document.setExpedient(this);
            }
        }
    }

    public void addDocument(Document document) {
        this.documents.add(document);
        document.setExpedient(this);
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "Expedient{" +
                "expedientId=" + expedientId +
                ", patientId=" + (patient != null ? patient.getUserId() : "null") +
                ", date=" + date +
                ", documentsCount=" + (documents != null ? documents.size() : "lazy") +
                ", authorization=" + authorization + '}';
    }
}

