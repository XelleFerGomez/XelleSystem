package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "fo_lc_19_calidad")
public class Calidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noRegistro;
    private String idTpl;
    private String laboratorioExterno;
    
    private String vihResultado;
    private String hepatitisBResultado;
    private String hepatitisCResultado;
    private String sifilisResultado;
    private String chagasResultado;
    
    // CAMPOS NUEVOS
    private String cmvResultado;
    private String toxoplasmaResultado;
    
    private String estadoFinal;
    
    @Column(columnDefinition = "TEXT")
    private String comentarios;
    
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() { fechaRegistro = LocalDateTime.now(); }
}