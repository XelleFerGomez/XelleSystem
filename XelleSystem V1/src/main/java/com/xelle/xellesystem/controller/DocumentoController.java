package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.DocumentoSGC;
import com.xelle.xellesystem.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin("*")
public class DocumentoController {

    @Autowired
    private DocumentoRepository documentoRepo;

    private final Path rootLocation = Paths.get("uploads-sgc");

    public DocumentoController() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar carpeta uploads");
        }
    }

    @GetMapping
    public List<DocumentoSGC> listarDocumentos() {
        return documentoRepo.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> subirDocumento(
            @RequestParam("file") MultipartFile file,
            @RequestParam("codigo") String codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("area") String area,
            @RequestParam("origen") String origen
    ) {
        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));

            DocumentoSGC doc = new DocumentoSGC();
            doc.setCodigo(codigo);
            doc.setNombre(nombre);
            doc.setTipo(tipo);
            doc.setArea(area);
            doc.setOrigen(origen);
            doc.setNombreArchivo(filename);
            
            documentoRepo.save(doc);
            
            return ResponseEntity.ok(Map.of("status", "ok"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("No se puede leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        documentoRepo.deleteById(id);
    }
}