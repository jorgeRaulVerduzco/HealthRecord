/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedientService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author JORGE
 */
@Configuration
public class FileStorageConfig {
  @Value("${file.upload-dir}")
  private String uploadDir;

  @Bean
  public Path uploadPath() {
    Path path = Paths.get(uploadDir).toAbsolutePath().normalize();
    try {
      Files.createDirectories(path);
    } catch (IOException e) {
      throw new RuntimeException("No se pudo crear directorio de uploads", e);
    }
    return path;
  }
}