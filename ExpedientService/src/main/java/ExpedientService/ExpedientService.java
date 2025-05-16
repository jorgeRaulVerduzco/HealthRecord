/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedientService;

/**
 *
 * @author JORGE
 */
import DaoHealthRecord.*;
import Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;
@Service
public class ExpedientService {

 private final ExpedientDAO expedientDao;
  private final PatientDAO patientDao; // Añadir DAO de pacientes
  private final Path uploadPath;

  @Autowired
  public ExpedientService(ExpedientDAO expedientDao, PatientDAO patientDao, Path uploadPath) {
    this.expedientDao = expedientDao;
    this.patientDao = patientDao;
    this.uploadPath = uploadPath;
  }

  /** Crea expediente si no existe y guarda el documento */
  public Expedient addDocumentToPatient(String curpPaciente, MultipartFile file) {
    Expedient exp = expedientDao.buscarPorCurpPaciente(curpPaciente);
    
    // Si no existe el expediente, verificamos si existe el paciente
    if (exp == null) {
      Patient patient = patientDao.buscarPorCurp(curpPaciente);
      
      // Si no existe el paciente, devolvemos error
      if (patient == null) {
        throw new RuntimeException("Paciente con CURP " + curpPaciente + " no encontrado");
      }
      
      // Crear nuevo expediente para el paciente existente
      exp = new Expedient();
      exp.setPatient(patient);
      exp.setDate(new Date());
      exp.setAuthorization(true); // Por defecto autorizado, ajustar según necesidades
      
      // Guardar el expediente
      expedientDao.crear(exp);
    }
    
    // guardar archivo en disco
    String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
    try {
      Path target = uploadPath.resolve(filename);
      Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException("Error guardando archivo", e);
    }
    
    // crear entidad Document
    Document doc = new Document();
    doc.setType("EXPEDIENT_IMAGE");
    doc.setNameDocument(filename);
    doc.setContentMultimedia(null); // no usamos BLOB, servimos desde disk
    exp.addDocument(doc);
    expedientDao.actualizar(exp);
    return exp;
  }

  /** Devuelve la ruta absoluta del recurso para servirlo */
  public Resource loadDocumentAsResource(String filename) {
    try {
      Path file = uploadPath.resolve(filename).normalize();
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("No se puede leer el archivo: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("URL inválida: " + filename, e);
    }
  }
  
  public Expedient getExpedientByCurp(String curpPaciente) {
    Expedient exp = expedientDao.buscarPorCurpPaciente(curpPaciente);
    if (exp == null) {
        throw new RuntimeException("Paciente con CURP " + curpPaciente + " no encontrado");
    }
    return exp;
  }
}
