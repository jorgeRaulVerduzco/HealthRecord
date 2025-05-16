package UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {
    "DaoHealthRecord",
    "UserService",
    "Dtos",
    "Entities" // <-- Esta línea debe estar exactamente así
})
@EnableJpaRepositories(basePackages = "DaoHealthRecord")
@EntityScan(basePackages = "Entities") // <-- AÑADE ESTA LÍNEA
public class HealthcareApplication {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "12345";
    String encodedPassword = encoder.encode(rawPassword);
    System.out.println("Nuevo hash para 12345: " + encodedPassword);
        SpringApplication.run(HealthcareApplication.class, args);
    }
}