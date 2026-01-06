package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "almacen_movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idInsumo;
    private String tipoMovimiento; // ENTRADA, SALIDA
    private Double cantidad;
    private String usuarioResponsable;
    private String motivo;
    
    private LocalDateTime fechaMovimiento;

    @PrePersist
    protected void onCreate() { fechaMovimiento = LocalDateTime.now(); }
}