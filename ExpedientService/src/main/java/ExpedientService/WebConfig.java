/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedientService;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
   registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }
   @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // Ajusta esta ruta al folder donde están tus .html, styles/, imgs/, etc.
    String frontPath = "file:///C:/Users/JORGE/OneDrive/Documentos/NetBeansProjects/HealthRecordProject/HealthCareWebApp/src/main/webapp/";

    registry.addResourceHandler("/**")              // todas las peticiones estáticas
            .addResourceLocations(frontPath)        // ruta de tu front en tu disco
            .setCachePeriod(0);                     // en desarrollo no cachea
  }
}
