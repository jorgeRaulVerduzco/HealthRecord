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
    "Entities" //
})
@EnableJpaRepositories(basePackages = "DaoHealthRecord")
@EntityScan(basePackages = "Entities") 
public class HealthcareApplication {
    public static void main(String[] args) {
        
        SpringApplication.run(HealthcareApplication.class, args);
    }
}