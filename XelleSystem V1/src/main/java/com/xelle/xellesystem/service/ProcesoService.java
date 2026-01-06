package com.xelle.xellesystem.service;

import com.xelle.xellesystem.model.Proceso;
import com.xelle.xellesystem.repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProcesoService {

    @Autowired
    private ProcesoRepository procesoRepository;

    // Obtener todos
    public List<Proceso> obtenerTodos() {
        return procesoRepository.findAll();
    }

    // Obtener por ID
    public Optional<Proceso> obtenerPorId(Long id) {
        return procesoRepository.findById(id);
    }

    // Eliminar
    public void eliminar(Long id) {
        procesoRepository.deleteById(id);
    }

    // LÓGICA MAESTRA: GUARDAR O ACTUALIZAR
    @Transactional
    public Proceso guardarOActualizar(Proceso proceso) {
        
        // CASO 1: ES NUEVO (No tiene ID) O VIENE CON FOLIO TEMPORAL
        if (proceso.getId() == null) {
            // Detectar si el folio es nulo, vacío o temporal (generado por JS tipo "PROC-17...")
            if (proceso.getNoRegistro() == null || proceso.getNoRegistro().isEmpty() || proceso.getNoRegistro().startsWith("PROC-")) {
                String nuevoFolio = generarSiguienteFolio();
                proceso.setNoRegistro(nuevoFolio);
            }
        } 
        
        // CASO 2: ES EDICIÓN
        // Spring detecta el ID automáticamente y hace un UPDATE respetando el folio existente.

        return procesoRepository.save(proceso);
    }

    // Método Privado para Generar Folios (Ej: FO-LC-20-001)
    private String generarSiguienteFolio() {
        Optional<Proceso> ultimoProceso = procesoRepository.findLastRecord();
        
        if (ultimoProceso.isPresent()) {
            String ultimoFolio = ultimoProceso.get().getNoRegistro();
            try {
                // Separamos por el guion (FO-LC-20-<consecutivo>)
                String[] partes = ultimoFolio.split("-");
                String consecutivoStr = partes[partes.length - 1]; // Toma el último bloque
                
                int consecutivo = Integer.parseInt(consecutivoStr);
                consecutivo++; // Sumamos 1
                
                // Formateamos a 3 dígitos
                return "FO-LC-20-" + String.format("%03d", consecutivo);
                
            } catch (Exception e) {
                // Si falla el parseo, devolvemos un fallback seguro
                return "FO-LC-20-" + System.currentTimeMillis(); 
            }
        } else {
            // Si la tabla está vacía, es el primero
            return "FO-LC-20-001";
        }
    }
}