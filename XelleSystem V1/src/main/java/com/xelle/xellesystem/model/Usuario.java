package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios_sistema")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo")
    private String nombre; // Mapeo: Java 'nombre' <-> BD 'nombre_completo'
    
    @Column(unique = true)
    private String username;
    
    private String password;
    private String role; 
}