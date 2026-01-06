package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "fo_lc_18_evaluacion") // <--- COINCIDE CON TU DB
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_registro", unique = true)
    private String noRegistro;
    
    @Column(name = "id_donante")
    private String idDonante;
    
    @Column(name = "fecha_proc")
    private LocalDate fechaProc;
    
    @Column(name = "hora_proc")
    private LocalTime horaProc;
    
    @Column(name = "id_placenta_generado")
    private String idPlacentaGenerado;
    
    @Column(name = "peso_total_g")
    private Double pesoTotalG;
    
    private String dimensiones;
    private String coloracion;
    
    @Column(name = "presencia_cordon")
    private String presenciaCordon; 
    
    private String fibrosis;
    private String coagulos;
    
    @Column(name = "integridad_membranas")
    private String integridadMembranas;
    
    @Column(name = "apta_proceso")
    private String aptaProceso; 
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
}