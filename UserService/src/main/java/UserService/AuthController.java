
// AuthController.java
package UserService;

import DaoHealthRecord.UserDAO;
import Dtos.LoginRequest;
import Dtos.LoginResponse;
import Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserDAO userDAO;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsServiceImpl userDetailsService, UserDAO userDAO) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userDAO = userDAO;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // 🔍 Imprimir información para depurar
            System.out.println("🔐 Intentando autenticar CURP: " + loginRequest.getCurp());

            Entities.User user = userDAO.buscarPorCurp(loginRequest.getCurp());
            if (user == null) {
                System.out.println("❌ Usuario no encontrado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
            }

            System.out.println("🧠 Password en la base: " + user.getPassword());
            System.out.println("🔑 Password ingresado: " + loginRequest.getPassword());

            PasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean match = encoder.matches(loginRequest.getPassword(), user.getPassword());
            System.out.println("✅ ¿Coinciden?: " + match);

            if (!match) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La contraseña no coincide con el hash");
            }

            // 🔐 Continuar con Spring Security si coincide
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getCurp(), loginRequest.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getCurp());
            String jwt = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(jwt));

        } catch (Exception e) {
            e.printStackTrace(); // 💥 Para ver detalles del error en consola
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
