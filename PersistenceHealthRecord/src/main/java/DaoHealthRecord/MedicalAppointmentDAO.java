/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoHealthRecord;

import Entities.HealthWorker;
import Entities.MedicalAppointment;
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
public class MedicalAppointmentDAO {

    private EntityManagerFactory emf;

    public MedicalAppointmentDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPu");
    }

    // Crear una cita médica
    public void crear(MedicalAppointment appointment) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
    }

    // Buscar una cita médica por ID
    public MedicalAppointment buscarPorId(long appointmentId) {
        EntityManager em = emf.createEntityManager();

        return em.find(MedicalAppointment.class, appointmentId);
    }

    // Actualizar una cita médica
    public void actualizar(MedicalAppointment appointment) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(appointment);
        em.getTransaction().commit();
    }

    // Eliminar una cita médica
    public void eliminar(MedicalAppointment appointment) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(appointment) ? appointment : em.merge(appointment));
        em.getTransaction().commit();
    }

    // Listar todas las citas médicas
    public List<MedicalAppointment> listarTodas() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<MedicalAppointment> query = em.createQuery(
                "SELECT m FROM MedicalAppointment m",
                MedicalAppointment.class);
        return query.getResultList();
    }

    // Listar citas médicas por paciente
    public List<MedicalAppointment> listarPorPaciente(Patient patient) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<MedicalAppointment> query = em.createQuery(
                "SELECT m FROM MedicalAppointment m WHERE m.patient = :patient",
                MedicalAppointment.class);
        query.setParameter("patient", patient);
        return query.getResultList();
    }

    // Listar citas médicas por trabajador de salud
    public List<MedicalAppointment> listarPorTrabajadorSalud(HealthWorker healthWorker) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<MedicalAppointment> query = em.createQuery(
                "SELECT m FROM MedicalAppointment m WHERE m.healthWorker = :healthWorker",
                MedicalAppointment.class);
        query.setParameter("healthWorker", healthWorker);
        return query.getResultList();
    }

    // Listar citas médicas por fechas
    public List<MedicalAppointment> listarPorFechas(Date fechaInicio, Date fechaFin) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<MedicalAppointment> query = em.createQuery(
                "SELECT m FROM MedicalAppointment m WHERE m.date BETWEEN :fechaInicio AND :fechaFin",
                MedicalAppointment.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

    // Listar citas médicas por estado
    public List<MedicalAppointment> listarPorEstado(String status) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<MedicalAppointment> query = em.createQuery(
                "SELECT m FROM MedicalAppointment m WHERE m.status = :status",
                MedicalAppointment.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    // Actualizar estado de una cita médica
    public void actualizarEstado(MedicalAppointment appointment, String status) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        appointment.setStatus(status);
        em.merge(appointment);
        em.getTransaction().commit();
    }

    // Buscar citas para un trabajador de salud específico en una fecha
    public List<MedicalAppointment> buscarCitasPorTrabajadorYFecha(HealthWorker healthWorker, Date fecha) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<MedicalAppointment> query = em.createQuery(
                "SELECT m FROM MedicalAppointment m WHERE m.healthWorker = :healthWorker "
                + "AND FUNCTION('DATE', m.date) = FUNCTION('DATE', :fecha)",
                MedicalAppointment.class);
        query.setParameter("healthWorker", healthWorker);
        query.setParameter("fecha", fecha);
        return query.getResultList();
    }

    // Verificar disponibilidad de horario
    public boolean verificarDisponibilidad(HealthWorker healthWorker, Date fecha) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(m) FROM MedicalAppointment m WHERE m.healthWorker = :healthWorker "
                + "AND m.date = :fecha",
                Long.class);
        query.setParameter("healthWorker", healthWorker);
        query.setParameter("fecha", fecha);
        Long count = query.getSingleResult();
        return count == 0; // Retorna true si no hay citas en ese horario
    }

    // Contar citas de un paciente
    public long contarCitasPaciente(Patient patient) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(m) FROM MedicalAppointment m WHERE m.patient = :patient",
                Long.class);
        query.setParameter("patient", patient);
        return query.getSingleResult();
    }
}
