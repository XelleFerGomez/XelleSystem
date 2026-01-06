package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fo_lc_20_cultivos")
public class Cultivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_proceso", nullable = false)
    @ToString.Exclude
    private Proceso proceso;

    @Column(name = "tipo_procedimiento") // EXP o DIG
    private String tipoProcedimiento;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "numero_consecutivo")
    private Integer numeroConsecutivo;

    @Column(name = "codigo_recipiente", unique = true)
    private String codigoRecipiente;

    @Column(name = "tipo_flask")
    private String tipoFlask;
}