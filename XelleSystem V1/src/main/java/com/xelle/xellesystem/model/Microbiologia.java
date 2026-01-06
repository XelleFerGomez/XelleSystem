package com.xelle.xellesystem.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data @Entity @Table(name = "fo_lc_31_microbiologia")
public class Microbiologia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String noRegistro;
    private LocalDate fechaIngreso;
    private String idMuestra;
    private String resAnaerobio;
    private String resAerobio;
    private String resHongos;
    private LocalDate fechaTermino;
    @Column(columnDefinition = "TEXT") private String comentarios;
    private String responsable;
}