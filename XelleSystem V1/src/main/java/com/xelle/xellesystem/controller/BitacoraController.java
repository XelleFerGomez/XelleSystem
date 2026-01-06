package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.BitacoraCultivo;
import com.xelle.xellesystem.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bitacoras")
@CrossOrigin("*")
public class BitacoraController {

    @Autowired
    private BitacoraRepository repository;

    @GetMapping
    public List<BitacoraCultivo> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public BitacoraCultivo create(@RequestBody BitacoraCultivo bitacora) {
        return repository.save(bitacora);
    }
    
    @GetMapping("/{id}")
    public BitacoraCultivo getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // --- ESTE ES EL MÉTODO QUE NECESITAS PARA BORRAR ---
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}