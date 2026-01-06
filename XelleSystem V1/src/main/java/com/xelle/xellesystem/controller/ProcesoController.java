package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.Proceso;
import com.xelle.xellesystem.service.ProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procesos")
@CrossOrigin("*")
public class ProcesoController {

    @Autowired
    private ProcesoService service; // Inyectamos el Servicio

    @GetMapping
    public List<Proceso> getAll() {
        return service.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<Proceso> create(@RequestBody Proceso proceso) {
        // Delegamos al servicio la lógica del folio
        Proceso guardado = service.guardarOActualizar(proceso);
        return ResponseEntity.ok(guardado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Proceso> getById(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.eliminar(id);
    }
}