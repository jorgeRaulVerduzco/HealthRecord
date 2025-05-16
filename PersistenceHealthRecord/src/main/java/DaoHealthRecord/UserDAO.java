package DaoHealthRecord;

import Entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    // Crear un usuario
    public void crear(User user) {
        em.persist(user);
    }

    // Buscar un usuario por ID
    public User buscarPorId(long userId) {
        return em.find(User.class, userId);
    }

    // Buscar un usuario por CURP
    public User buscarPorCurp(String curp) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.curp = :curp", User.class);
        query.setParameter("curp", curp);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Actualizar un usuario
    public void actualizar(User user) {
        em.merge(user);
    }

    // Eliminar un usuario
    public void eliminar(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    // Listar todos los usuarios
    public List<User> listarTodos() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    // Validar credenciales (login)
    public User validarCredenciales(String curp, String password) {
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
        User userExistente = buscarPorCurp(curp);
        if (userExistente != null) {
            userModificado.setUserId(userExistente.getUserId());
            em.merge(userModificado);
        }
    }

    // Eliminar usuario por CURP
    public void eliminarPorCurp(String curp) {
        User user = buscarPorCurp(curp);
        if (user != null) {
            em.remove(em.contains(user) ? user : em.merge(user));
        }
    }
}
