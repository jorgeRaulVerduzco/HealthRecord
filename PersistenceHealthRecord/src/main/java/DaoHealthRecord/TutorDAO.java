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
public class TutorDAO {

    private EntityManagerFactory emf;

    public TutorDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");

    }

    // Crear un tutor
    public void crear(Tutor tutor) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(tutor);
        em.getTransaction().commit();
    }

    // Buscar un tutor por ID
    public Tutor buscarPorId(long tutorId) {
        EntityManager em = emf.createEntityManager();

        return em.find(Tutor.class, tutorId);
    }

    // Buscar un tutor por CURP
    public Tutor buscarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Tutor> query = em.createQuery(
                "SELECT t FROM Tutor t WHERE t.curp = :curp",
                Tutor.class);
        query.setParameter("curp", curp);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar un tutor
    public void actualizar(Tutor tutor) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(tutor);
        em.getTransaction().commit();
    }

    // Eliminar un tutor
    public void eliminar(Tutor tutor) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(tutor) ? tutor : em.merge(tutor));
        em.getTransaction().commit();
    }

    // Listar todos los tutores
    public List<Tutor> listarTodos() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Tutor> query = em.createQuery(
                "SELECT t FROM Tutor t",
                Tutor.class);
        return query.getResultList();
    }

    // Buscar tutores mayores de edad
    public List<Tutor> buscarTutoresMayoresDeEdad() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Tutor> query = em.createQuery(
                "SELECT t FROM Tutor t WHERE t.age >= 18",
                Tutor.class);
        return query.getResultList();
    }

    // Obtener pacientes a cargo de un tutor
    public List<Patient> obtenerPacientes(Tutor tutor) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.tutor = :tutor",
                Patient.class);
        query.setParameter("tutor", tutor);
        return query.getResultList();
    }

    // Validar datos biométricos del tutor
    public Tutor validarBiometricos(String biometricData) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Tutor> query = em.createQuery(
                "SELECT t FROM Tutor t WHERE t.biometricData = :biometricData",
                Tutor.class);
        query.setParameter("biometricData", biometricData);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Verificar si es tutor válido (mayor de edad)
    public boolean esTutorValido(Tutor tutor) {
        return tutor != null && tutor.getAge() >= 18;
    }

    public void actualizarPorCurp(String curp, Tutor tutorModificado) {
        EntityManager em = emf.createEntityManager();

        Tutor tutorExistente = buscarPorCurp(curp);
        if (tutorExistente != null) {
            // Actualizamos solo los datos del tutor, manteniendo su ID y relaciones
            tutorModificado.setUserId(tutorExistente.getUserId());

            // Si el tutor modificado no tiene pacientes asignados, mantener los existentes
            if (tutorModificado.getPatients() == null || tutorModificado.getPatients().isEmpty()) {
                tutorModificado.setPatients(tutorExistente.getPatients());
            }

            em.getTransaction().begin();
            em.merge(tutorModificado);
            em.getTransaction().commit();
        }
    }

    public void eliminarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        Tutor tutor = buscarPorCurp(curp);
        if (tutor != null) {
            em.getTransaction().begin();
            em.remove(em.contains(tutor) ? tutor : em.merge(tutor));
            em.getTransaction().commit();
        }
    }
}
