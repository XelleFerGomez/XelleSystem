package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.Evaluacion;
import com.xelle.xellesystem.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin("*")
public class EvaluacionController {

    @Autowired
    private EvaluacionRepository repository;

    @GetMapping
    public List<Evaluacion> getAll() { return repository.findAll(); }

    @PostMapping
    public Evaluacion create(@RequestBody Evaluacion evaluacion) {
        return repository.save(evaluacion);
    }
    
    @GetMapping("/{id}")
    public Evaluacion getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}