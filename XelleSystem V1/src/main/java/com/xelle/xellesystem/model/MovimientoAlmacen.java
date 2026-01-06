package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial_almacen")
public class MovimientoAlmacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idItem; // ID del InsumoAlmacen
    private String tipoMovimiento; // ENTRADA, SALIDA
    private Double cantidad;
    private String usuarioResponsable;
    private String motivo;
    
    private LocalDateTime fechaMovimiento;

    @PrePersist
    protected void onCreate() { fechaMovimiento = LocalDateTime.now(); }
}