package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.Usuario;
import com.xelle.xellesystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credenciales) {
        String username = credenciales.get("username");
        String password = credenciales.get("password");

        Map<String, Object> response = new HashMap<>();
        
        // CORRECCIÓN AQUÍ: Agregamos .orElse(null)
        Usuario usuario = usuarioRepo.findByUsername(username).orElse(null);

        if (usuario != null && usuario.getPassword().equals(password)) {
            response.put("status", "ok");
            response.put("nombre", usuario.getNombre()); 
            response.put("role", usuario.getRole());
        } else {
            response.put("status", "error");
            response.put("message", "Credenciales incorrectas");
        }

        return response;
    }
}