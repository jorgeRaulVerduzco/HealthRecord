/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JORGE
 */
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
    private List<Document> documents;

    @Column(name = "authorization")
    private boolean authorization;

    public Expedient() {
    }

    public Expedient(Patient patient, Date date, List<Document> documents, boolean authorization) {
        this.patient = patient;
        this.date = date;
        this.documents = documents;
        this.authorization = authorization;
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
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "Expedient{" + "expedientId=" + expedientId + ", patient=" + patient + ", date=" + date + ", documents=" + documents + ", authorization=" + authorization + '}';
    }
}
