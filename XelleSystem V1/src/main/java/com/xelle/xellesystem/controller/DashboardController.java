package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.Recepcion;
import com.xelle.xellesystem.repository.BitacoraRepository;
import com.xelle.xellesystem.repository.ProcesoRepository;
import com.xelle.xellesystem.repository.RecepcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired private ProcesoRepository procesoRepo;
    @Autowired private BitacoraRepository bitacoraRepo;
    @Autowired private RecepcionRepository recepcionRepo;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        long totalRecepciones = recepcionRepo.count();
        long totalProcesos = procesoRepo.count();
        long totalBitacoras = bitacoraRepo.count();

        // Cálculos
        long vialesTotales = totalProcesos * 5;
        long celulasTotales = vialesTotales * 1_000_000;

        stats.put("total_viales", vialesTotales); 
        stats.put("total_celulas", celulasTotales); // CORRECCIÓN: Usamos la variable aquí
        stats.put("total_dosificadas", totalProcesos * 1_500_000);
        stats.put("total_activos", totalProcesos);
        stats.put("total_bitacoras", totalBitacoras); 
        stats.put("alertas_num", totalRecepciones > totalProcesos ? (totalRecepciones - totalProcesos) : 0);

        // Desglose simulado (Usamos las variables calculadas)
        stats.put("trabajo_viales", (long)(vialesTotales * 0.4));
        stats.put("trabajo_celulas", (long)(celulasTotales * 0.4)); // Usada aquí también
        
        stats.put("maestro_viales", (long)(vialesTotales * 0.4));
        stats.put("maestro_celulas", (long)(celulasTotales * 0.4)); // Usada aquí también
        
        stats.put("prod_viales", (long)(vialesTotales * 0.2));
        stats.put("prod_celulas", (long)(celulasTotales * 0.2)); // Usada aquí también

        // Actividad Reciente
        List<Map<String, String>> actividad = new ArrayList<>();
        List<Recepcion> recs = recepcionRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        
        recs.stream().limit(3).forEach(r -> {
            Map<String, String> item = new HashMap<>();
            item.put("tipo", "RECEPCIÓN");
            item.put("color", "#2ecc71");
            item.put("titulo", "Rec: " + r.getNoRegistro());
            item.put("fecha", r.getFechaRecepcion() != null ? r.getFechaRecepcion().toString() : "-");
            actividad.add(item);
        });
        
        stats.put("actividad_reciente", actividad);
        return stats;
    }
}