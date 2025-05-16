// PatientController.java
package UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @GetMapping("/profile")
    public ResponseEntity<String> profile(Authentication auth) {
        return ResponseEntity.ok("Hola paciente: " + auth.getName());
    }
}
