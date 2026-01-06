package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documentos_sgc")
public class DocumentoSGC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private String tipo;      // MC, PO, PR, IT, FO
    private String area;      // LC, DG, AC
    private String version;
    private String estado;    // Vigente, Obsoleto
    
    @Column(name = "nombre_archivo")
    private String nombreArchivo; // Nombre físico UUID
    
    @Column(name = "ruta_descarga")
    private String rutaDescarga;  // URL API
    
    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida;
    
    private String origen; // 'MANUAL' o 'SISTEMA'

    @PrePersist
    protected void onCreate() {
        fechaSubida = LocalDateTime.now();
        if(version == null) version = "1.0";
        if(estado == null) estado = "Vigente";
    }
}