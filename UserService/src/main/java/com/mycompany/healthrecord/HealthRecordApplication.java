/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.healthrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaRepositories("com.mycompany.healthrecord.repository")
@EntityScan("com.mycompany.healthrecord.entities")
public class HealthRecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthRecordApplication.class, args);
    }
}