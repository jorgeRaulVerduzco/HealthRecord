/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserService;

import DaoHealthRecord.UserDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author yalam
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String curp) throws UsernameNotFoundException {
        Entities.User user = userDAO.buscarPorCurp(curp);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con CURP: " + curp);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getCurp(), 
                user.getPassword(),
                user.getAuthorities());
    }
}