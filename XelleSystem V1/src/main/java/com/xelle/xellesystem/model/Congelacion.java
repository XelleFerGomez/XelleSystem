package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "fo_lc_20_congelacion")
public class Congelacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_proceso", nullable = false)
    @ToString.Exclude
    private Proceso proceso;

    @Column(name = "numero_viales")
    private Integer numeroViales;

    @Column(name = "concentracion_celular")
    private String concentracionCelular;

    @Column(name = "volumen_total")
    private String volumenTotal;

    @Column(name = "volumen_por_vial")
    private String volumenPorVial;

    @Column(name = "medio_congelacion")
    private String medioCongelacion;

    @Column(name = "volumen_medio")
    private String volumenMedio;

    @Column(name = "porcentaje_viabilidad")
    private String porcentajeViabilidad;

    @Column(name = "tipo_procedimiento")
    private String tipoProcedimiento;

    @Column(name = "codigo_lote") // Trazabilidad con Flask
    private String codigoLote;
}