/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoHealthRecord;

import Entities.Document;
import Entities.Expedient;
import Entities.HealthWorker;
import Entities.Patient;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author JORGE
 */
public class ExpedientDAO {

    private EntityManagerFactory emf;

    public ExpedientDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPu");
    }

    // Crear un expediente
    public void crear(Expedient expedient) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(expedient);
        em.getTransaction().commit();
    }

    // Buscar un expediente por ID
    public Expedient buscarPorId(long expedientId) {
        EntityManager em = emf.createEntityManager();

        return em.find(Expedient.class, expedientId);
    }

    // Buscar expediente por paciente
    public Expedient buscarPorPaciente(Patient patient) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Expedient> query = em.createQuery(
                "SELECT e FROM Expedient e WHERE e.patient = :patient",
                Expedient.class);
        query.setParameter("patient", patient);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar un expediente
    public void actualizar(Expedient expedient) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(expedient);
        em.getTransaction().commit();
    }

    // Eliminar un expediente
    public void eliminar(Expedient expedient) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(expedient) ? expedient : em.merge(expedient));
        em.getTransaction().commit();
    }

    // Listar todos los expedientes
    public List<Expedient> listarTodos() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Expedient> query = em.createQuery(
                "SELECT e FROM Expedient e",
                Expedient.class);
        return query.getResultList();
    }

    // Cambiar el estado de autorización de un expediente
    public void cambiarAutorizacion(Expedient expedient, boolean autorizacion) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        expedient.setAuthorization(autorizacion);
        em.merge(expedient);
        em.getTransaction().commit();
    }

    // Agregar documento al expediente
    public void agregarDocumento(Expedient expedient, Document document) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        document.setExpedient(expedient);
        em.persist(document);
        expedient.getDocuments().add(document);
        em.merge(expedient);
        em.getTransaction().commit();
    }

    // Listar documentos de un expediente
    public List<Document> listarDocumentos(Expedient expedient) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Document> query = em.createQuery(
                "SELECT d FROM Document d WHERE d.expedient = :expedient",
                Document.class);
        query.setParameter("expedient", expedient);
        return query.getResultList();
    }

    // Verificar si un trabajador de salud tiene acceso a un expediente específico
    public boolean verificarAcceso(Expedient expedient, HealthWorker healthWorker) {
        // Verificar si el expediente está autorizado
        if (!expedient.isAuthorization()) {
            return false;
        }

        // Verificar si el trabajador de salud tiene cédula profesional válida
        if (healthWorker.getProfessionalLicense() == null
                || healthWorker.getProfessionalLicense().isEmpty()) {
            return false;
        }

        return true;
    }

    // Listar expedientes por fecha de creación
    public List<Expedient> listarPorFechaCreacion(Date fechaInicio, Date fechaFin) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Expedient> query = em.createQuery(
                "SELECT e FROM Expedient e WHERE e.date BETWEEN :fechaInicio AND :fechaFin",
                Expedient.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }
}
