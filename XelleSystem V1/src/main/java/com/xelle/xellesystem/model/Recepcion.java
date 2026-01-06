package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fo_lc_17_recepcion")
public class Recepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_registro", unique = true)
    private String noRegistro;

    private String nombreMadre;
    private String expediente;
    private String medicoTratante;
    private String cedulaMedico;
    private String idMateriaPrima;
    
    // ESTE ES EL CAMPO QUE EL DASHBOARD BUSCA:
    @Column(name = "fecha_recepcion")
    private LocalDate fechaRecepcion;

    private String sexo;
    private String semanasGestacion;
    private String pesoGramos;
    private String tallaCm;
    
    private String fechaNacimiento; // Puede ser String o LocalDateTime
    private String hospitalSede;
    private String estadoMuestra;
    private String temperaturaRecepcion;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
}