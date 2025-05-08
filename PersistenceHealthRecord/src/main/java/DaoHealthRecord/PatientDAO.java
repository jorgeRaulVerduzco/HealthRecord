/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoHealthRecord;

import Entities.Patient;
import Entities.Tutor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author JORGE
 */
public class PatientDAO {

    private EntityManagerFactory emf;

    public PatientDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");

    }

    // Crear un paciente
    public void crear(Patient patient) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    // Buscar un paciente por ID
    public Patient buscarPorId(long patientId) {
        EntityManager em = emf.createEntityManager();

        return em.find(Patient.class, patientId);
    }

    // Buscar un paciente por CURP
    public Patient buscarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.curp = :curp",
                Patient.class);
        query.setParameter("curp", curp);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar un paciente
    public void actualizar(Patient patient) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(patient);
        em.getTransaction().commit();
    }

    // Eliminar un paciente
    public void eliminar(Patient patient) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(patient) ? patient : em.merge(patient));
        em.getTransaction().commit();
    }

    // Listar todos los pacientes
    public List<Patient> listarTodos() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p",
                Patient.class);
        return query.getResultList();
    }

    // Listar pacientes por tutor
    public List<Patient> listarPorTutor(Tutor tutor) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.tutor = :tutor",
                Patient.class);
        query.setParameter("tutor", tutor);
        return query.getResultList();
    }

    // Asignar tutor a paciente
    public void asignarTutor(Patient patient, Tutor tutor) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        patient.setTutor(tutor);
        em.merge(patient);
        em.getTransaction().commit();
    }

    // Buscar pacientes menores de edad sin tutor
    public List<Patient> buscarMenoresSinTutor() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.age < 18 AND p.tutor IS NULL",
                Patient.class);
        return query.getResultList();
    }

    // Validar datos biométricos del paciente
    public Patient validarBiometricos(String biometricData) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.biometricData = :biometricData",
                Patient.class);
        query.setParameter("biometricData", biometricData);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizarPorCurp(String curp, Patient patientModificado) {
        EntityManager em = emf.createEntityManager();

        Patient patientExistente = buscarPorCurp(curp);
        if (patientExistente != null) {
            // Actualizamos solo los datos del paciente, manteniendo su ID y relaciones
            patientModificado.setUserId(patientExistente.getUserId());

            // Si no se especifica un tutor, mantener el existente
            if (patientModificado.getTutor() == null) {
                patientModificado.setTutor(patientExistente.getTutor());
            }

            // Si no se especifica un expediente, mantener el existente
            if (patientModificado.getExpedient() == null) {
                patientModificado.setExpedient(patientExistente.getExpedient());
            }

            em.getTransaction().begin();
            em.merge(patientModificado);
            em.getTransaction().commit();
        }
    }

    public void eliminarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        Patient patient = buscarPorCurp(curp);
        if (patient != null) {
            em.getTransaction().begin();
            em.remove(em.contains(patient) ? patient : em.merge(patient));
            em.getTransaction().commit();
        }
    }
}
