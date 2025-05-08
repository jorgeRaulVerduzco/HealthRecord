/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoHealthRecord;

import Entities.HealthWorker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author JORGE
 */
public class HealthWorkerDAO {

    private EntityManagerFactory emf;

    public HealthWorkerDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    // Crear un trabajador de salud
    public void crear(HealthWorker healthWorker) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(healthWorker);
        em.getTransaction().commit();
    }

    // Buscar un trabajador de salud por ID
    public HealthWorker buscarPorId(long healthWorkerId) {
        EntityManager em = emf.createEntityManager();

        return em.find(HealthWorker.class, healthWorkerId);
    }

    // Buscar un trabajador de salud por cédula profesional
    public HealthWorker buscarPorCedulaProfesional(String professionalLicense) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<HealthWorker> query = em.createQuery(
                "SELECT h FROM HealthWorker h WHERE h.professionalLicense = :professionalLicense",
                HealthWorker.class);
        query.setParameter("professionalLicense", professionalLicense);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Buscar un trabajador de salud por CURP
    public HealthWorker buscarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<HealthWorker> query = em.createQuery(
                "SELECT h FROM HealthWorker h WHERE h.curp = :curp",
                HealthWorker.class);
        query.setParameter("curp", curp);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar un trabajador de salud
    public void actualizar(HealthWorker healthWorker) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(healthWorker);
        em.getTransaction().commit();
    }

    // Eliminar un trabajador de salud
    public void eliminar(HealthWorker healthWorker) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(healthWorker) ? healthWorker : em.merge(healthWorker));
        em.getTransaction().commit();
    }

    // Listar todos los trabajadores de salud
    public List<HealthWorker> listarTodos() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<HealthWorker> query = em.createQuery(
                "SELECT h FROM HealthWorker h",
                HealthWorker.class);
        return query.getResultList();
    }

    // Validar datos biométricos del trabajador de salud
    public HealthWorker validarBiometricos(String biometricData) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<HealthWorker> query = em.createQuery(
                "SELECT h FROM HealthWorker h WHERE h.biometricData = :biometricData",
                HealthWorker.class);
        query.setParameter("biometricData", biometricData);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Verificar si tiene cédula profesional válida
    public boolean tieneCedulaProfesionalValida(HealthWorker healthWorker) {
        return healthWorker != null
                && healthWorker.getProfessionalLicense() != null
                && !healthWorker.getProfessionalLicense().isEmpty();
    }

    public void actualizarPorProfessionalLicense(String professionalLicense, HealthWorker healthWorkerModificado) {
        EntityManager em = emf.createEntityManager();

        HealthWorker healthWorkerExistente = buscarPorCedulaProfesional(professionalLicense);
        if (healthWorkerExistente != null) {
            // Actualizamos solo los datos del trabajador de salud, manteniendo su ID
            healthWorkerModificado.setUserId(healthWorkerExistente.getUserId());

            em.getTransaction().begin();
            em.merge(healthWorkerModificado);
            em.getTransaction().commit();
        }
    }

    public void eliminarPorProfessionalLicense(String professionalLicense) {
        EntityManager em = emf.createEntityManager();

        HealthWorker healthWorker = buscarPorCedulaProfesional(professionalLicense);
        if (healthWorker != null) {
            em.getTransaction().begin();
            em.remove(em.contains(healthWorker) ? healthWorker : em.merge(healthWorker));
            em.getTransaction().commit();
        }
    }

    public void actualizarPorCurp(String curp, HealthWorker healthWorkerModificado) {
        EntityManager em = emf.createEntityManager();

        HealthWorker healthWorkerExistente = buscarPorCurp(curp);
        if (healthWorkerExistente != null) {
            healthWorkerModificado.setUserId(healthWorkerExistente.getUserId());

            em.getTransaction().begin();
            em.merge(healthWorkerModificado);
            em.getTransaction().commit();
        }
    }

    public void eliminarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        HealthWorker healthWorker = buscarPorCurp(curp);
        if (healthWorker != null) {
            em.getTransaction().begin();
            em.remove(em.contains(healthWorker) ? healthWorker : em.merge(healthWorker));
            em.getTransaction().commit();
        }
    }
}
