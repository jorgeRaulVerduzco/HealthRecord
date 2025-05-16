// UserDetailsServiceImpl.java
package UserService;

import DaoHealthRecord.UserDAO;
import Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String curp) throws UsernameNotFoundException {
        User user = userDAO.buscarPorCurp(curp);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con CURP: " + curp);
        }
        return user;
    }
}