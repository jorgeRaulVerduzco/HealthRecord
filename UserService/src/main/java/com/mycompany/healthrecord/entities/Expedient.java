/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthrecord.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
        this.documents = new ArrayList<>();
    }

    public Expedient(Patient patient, Date date, boolean authorization) {
        this.patient = patient;
        this.date = date;
        this.authorization = authorization;
        this.documents = new ArrayList<>();
    }

    // Constructor para compatibilidad con el código existente
    public Expedient(Patient patient, Date date, List<Document> documents, boolean authorization) {
        this.patient = patient;
        this.date = date;
        this.authorization = authorization;
        this.documents = new ArrayList<>();
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
        documents.add(document);
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
        return "Expedient{"
                + "expedientId=" + expedientId
                + ", patientId=" + (patient != null ? patient.getPatientId() : "null")
                + ", date=" + date
                + ", documentsCount=" + (documents != null && documents instanceof Collection<?> ? ((Collection<?>) documents).size() : "lazy")
                + ", authorization=" + authorization + '}';
    }
}
