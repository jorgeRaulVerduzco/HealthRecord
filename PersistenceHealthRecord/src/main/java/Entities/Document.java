/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author JORGE
 */
@Entity
@Table(name = "Document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private long documentID;

    @Column(length = 50)
    private String type;

    @Column(name = "name_document", length = 255)
    private String nameDocument;

    @Lob
    @Column(name = "content_multimedia")
    private String contentMultimedia;

    @ManyToOne
    @JoinColumn(name = "expedient_id")
    private Expedient expedient;

    public Document() {
    }

    public Document(String type, String nameDocument, String contentMultimedia) {
        this.type = type;
        this.nameDocument = nameDocument;
        this.contentMultimedia = contentMultimedia;
    }

    public long getDocumentID() {
        return documentID;
    }

    public void setDocumentID(long documentID) {
        this.documentID = documentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public String getContentMultimedia() {
        return contentMultimedia;
    }

    public void setContentMultimedia(String contentMultimedia) {
        this.contentMultimedia = contentMultimedia;
    }

    public Expedient getExpedient() {
        return expedient;
    }

    public void setExpedient(Expedient expedient) {
        this.expedient = expedient;
    }

    @Override
    public String toString() {
        return "Document{" + "documentID=" + documentID + ", type=" + type + ", nameDocument=" + nameDocument + ", contentMultimedia=" + contentMultimedia + '}';
    }
}
