package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fo_lc_20_insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_proceso", nullable = false)
    @ToString.Exclude
    private Proceso proceso;

    @Column(name = "nombre_insumo")
    private String nombreInsumo;

    @Column(name = "marca_proveedor")
    private String marcaProveedor;

    private String lote;
    private LocalDate caducidad;
}