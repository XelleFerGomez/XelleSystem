package com.xelle.xellesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fo_lc_20_proceso") // Coincide con tu esquema
public class Proceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noRegistro;
    private String idLinea; // id_linea_tpl
    private LocalDate fechaProceso;
    private String responsable;
    
    private String seCongelo; // SI/NO
    private String ubicacionTanque;
    
    // Guardamos los datos de las sub-tablas como Texto/JSON temporalmente
    // para que la aplicación funcione sin rediseñar toda la base de datos relacional ahora.
    @Column(columnDefinition = "TEXT")
    private String datosCongelacion; 
    
    @Column(columnDefinition = "TEXT")
    private String datosCultivos; 

    private boolean metodoExplantes;
    private boolean metodoDigestion;
}