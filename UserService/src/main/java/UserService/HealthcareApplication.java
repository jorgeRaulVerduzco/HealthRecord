/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserService;

/**
 *
 * @author yalam
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "DaoHealthRecord")
@SpringBootApplication(scanBasePackages = {"DaoHealthRecord","Entities","useService"})
public class HealthcareApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthcareApplication.class, args);
    }
}