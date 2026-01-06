package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.Recepcion;
import com.xelle.xellesystem.repository.RecepcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recepciones")
@CrossOrigin("*")
public class RecepcionController {

    @Autowired
    private RecepcionRepository repository; // La variable se llama "repository"

    @GetMapping
    public List<Recepcion> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Recepcion create(@RequestBody Recepcion recepcion) {
        return repository.save(recepcion);
    }
    
    @GetMapping("/{id}")
    public Recepcion getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // ELIMINAR (Corrección del error)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}