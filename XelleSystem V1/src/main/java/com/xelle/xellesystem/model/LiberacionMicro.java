package com.xelle.xellesystem.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data @Entity @Table(name = "fo_lc_32_liberacion")
public class LiberacionMicro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String noRegistro;
    private String idMuestra;
    private LocalDate fechaIngreso;
    private String resultadoFinal;
    private LocalDate fechaTermino;
    @Column(columnDefinition = "TEXT") private String detallesJson;
    private String responsable;
}