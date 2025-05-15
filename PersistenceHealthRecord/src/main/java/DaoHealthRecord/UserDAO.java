/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoHealthRecord;

import Entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDAO {

    private EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    // Crear un usuario
    public void crear(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    // Buscar un usuario por ID
    public User buscarPorId(long userId) {
        EntityManager em = emf.createEntityManager();

        return em.find(User.class, userId);
    }

    // Buscar un usuario por CURP
    public User buscarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.curp = :curp",
                User.class);
        query.setParameter("curp", curp);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar un usuario
    public void actualizar(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    // Eliminar un usuario
    public void eliminar(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(user) ? user : em.merge(user));
        em.getTransaction().commit();
    }

    // Listar todos los usuarios
    public List<User> listarTodos() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u",
                User.class);
        return query.getResultList();
    }

    // Validar credenciales (login)
    public User validarCredenciales(String curp, String password) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.curp = :curp AND u.password = :password",
                User.class);
        query.setParameter("curp", curp);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Validar datos biométricos
    public User validarBiometricos(String biometricData) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.biometricData = :biometricData",
                User.class);
        query.setParameter("biometricData", biometricData);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar usuario por CURP
    public void actualizarPorCurp(String curp, User userModificado) {
        EntityManager em = emf.createEntityManager();

        User userExistente = buscarPorCurp(curp);
        if (userExistente != null) {
            userModificado.setUserId(userExistente.getUserId());

            em.getTransaction().begin();
            em.merge(userModificado);
            em.getTransaction().commit();
        }
    }

    // Eliminar usuario por CURP
    public void eliminarPorCurp(String curp) {
        EntityManager em = emf.createEntityManager();

        User user = buscarPorCurp(curp);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            em.getTransaction().commit();
        }
    }
}