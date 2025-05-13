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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/expedient")
public class ExpedientController {

    private final ExpedientService expedientService;

    @Autowired
    public ExpedientController(ExpedientService expedientService) {
        this.expedientService = expedientService;
    }

    /**
     * Muestra el formulario de upload
     */
    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("curpPaciente", "");
        return "uploadForm";  // Thymeleaf: src/main/resources/templates/uploadForm.html
    }

    /**
     * Procesa el envío del formulario
     */
    @PostMapping("/upload")
    public String handleFileUpload(
            @RequestParam("curpPaciente") String curp,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes ra) {

        expedientService.addDocumentToPatient(curp, file);
        ra.addFlashAttribute("message", "Archivo subido correctamente.");
        return "redirect:/expedient/view/" + curp;
    }

    /**
     * Muestra la vista con la imagen ya cargada
     */
    @GetMapping("/view/{curp}")
    public String viewExpedient(@PathVariable String curp, Model model) {
        Expedient exp = expedientService.getExpedientByCurp(curp);  // ✅ Acceso correcto

        String filename = exp.getDocuments().stream()
                .filter(d -> "EXPEDIENT_IMAGE".equals(d.getType()))
                .findFirst()
                .map(Document::getNameDocument)
                .orElse("no-image.png");

        model.addAttribute("imageName", filename);
        model.addAttribute("curpPaciente", curp);
        return "viewExpedient";
    }

    /**
     * Sirve el recurso estático (imagen)
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = expedientService.loadDocumentAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
