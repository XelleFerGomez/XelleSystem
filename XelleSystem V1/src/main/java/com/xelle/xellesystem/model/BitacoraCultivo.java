package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fo_lc_21_bitacora")
public class BitacoraCultivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_registro", unique = true)
    private String noRegistro;

    @Column(name = "fecha_operacion")
    private LocalDate fechaOperacion;

    private String responsable;

    @Column(name = "datos_insumos", columnDefinition = "TEXT")
    private String datosInsumos;

    @Column(name = "datos_flasks", columnDefinition = "TEXT")
    private String datosFlasks;

    @Column(name = "datos_cosechas", columnDefinition = "TEXT")
    private String datosCosechas;

    @Column(columnDefinition = "TEXT")
    private String incidencias;
}