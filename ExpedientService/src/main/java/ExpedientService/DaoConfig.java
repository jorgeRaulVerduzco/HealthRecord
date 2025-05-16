/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedientService;

import DaoHealthRecord.ExpedientDAO;
import DaoHealthRecord.PatientDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    /**
     * Registra tu ExpedientDAO como bean de Spring,
     * para poder inyectarlo en ExpedientService.
     */
    @Bean
    public ExpedientDAO expedientDAO() {
        return new ExpedientDAO();
    }
    @Bean
    public PatientDAO patientDAO() {
        return new PatientDAO();
    }
}