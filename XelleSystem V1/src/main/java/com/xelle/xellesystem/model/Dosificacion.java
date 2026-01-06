package com.xelle.xellesystem.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data @Entity @Table(name = "fo_dosificacion")
public class Dosificacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String noRegistro;
    private LocalDate semanaInicio;
    private LocalDate semanaFin;
    private String responsable;
    private String lineaCelular;
    @Column(columnDefinition = "TEXT") private String datosJson;
}