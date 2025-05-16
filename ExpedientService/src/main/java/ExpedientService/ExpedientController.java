/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedientService;

/**
 *
 * @author JORGE
 */
import Entities.*;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/expedient")
public class ExpedientController {

  private final ExpedientService service;

  @Autowired
  public ExpedientController(ExpedientService service) {
    this.service = service;
  }

  @PostMapping("/upload")
  public ResponseEntity<Map<String,String>> upload(
      @RequestParam String curpPaciente,
      @RequestParam MultipartFile file) {
    service.addDocumentToPatient(curpPaciente, file);
    return ResponseEntity.ok(Map.of("message", "Archivo subido correctamente"));
  }

  @GetMapping("/{curp}")
  public ResponseEntity<ExpedientDTO> getExpedient(@PathVariable String curp) {
    Expedient exp = service.getExpedientByCurp(curp);
    ExpedientDTO dto = new ExpedientDTO(
      exp.getPatient().getCurp(),
      exp.getDocuments().stream()
         .map(d -> new DocumentDTO(d.getType(), d.getNameDocument()))
         .toList()
    );
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    Resource file = service.loadDocumentAsResource(filename);
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
      .body(file);
  }

  // DTOs internos
  public record ExpedientDTO(String curpPaciente, List<DocumentDTO> documentos){}
  public record DocumentDTO(String type, String name){}
}