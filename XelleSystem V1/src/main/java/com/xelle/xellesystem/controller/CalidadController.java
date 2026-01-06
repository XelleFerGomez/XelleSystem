package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.Calidad;
import com.xelle.xellesystem.repository.CalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calidad")
@CrossOrigin("*")
public class CalidadController {

    @Autowired
    private CalidadRepository repository;

    @GetMapping
    public List<Calidad> getAll() { return repository.findAll(); }

    @PostMapping
    public Calidad create(@RequestBody Calidad calidad) {
        return repository.save(calidad);
    }
    
    @GetMapping("/{id}")
    public Calidad getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}