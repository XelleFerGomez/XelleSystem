package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_almacen")
public class InsumoAlmacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProducto;
    private String presentacion;
    private String lote;
    private LocalDate caducidad;
    private Double cantidadActual;
    private String ubicacion;
    
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() { fechaRegistro = LocalDateTime.now(); }
}