package com.xelle.xellesystem.controller;

import com.xelle.xellesystem.model.InsumoAlmacen;
import com.xelle.xellesystem.model.MovimientoAlmacen;
import com.xelle.xellesystem.repository.InsumoAlmacenRepository;
import com.xelle.xellesystem.repository.MovimientoAlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen")
@CrossOrigin("*")
public class AlmacenController {

    @Autowired private InsumoAlmacenRepository itemRepo;
    @Autowired private MovimientoAlmacenRepository movRepo;

    @GetMapping("/items")
    public List<InsumoAlmacen> getInventario() {
        return itemRepo.findAll();
    }

    @PostMapping("/items")
    public InsumoAlmacen crearItem(@RequestBody InsumoAlmacen item) {
        if(item.getCantidadActual() == null) item.setCantidadActual(0.0);
        return itemRepo.save(item);
    }

    @PostMapping("/movimiento")
    public MovimientoAlmacen registrarMovimiento(@RequestBody MovimientoAlmacen mov) {
        // 1. Guardar Historial
        MovimientoAlmacen guardado = movRepo.save(mov);

        // 2. Actualizar Stock
        InsumoAlmacen item = itemRepo.findById(mov.getIdItem()).orElse(null);
        if(item != null) {
            double actual = item.getCantidadActual();
            if("ENTRADA".equals(mov.getTipoMovimiento())) {
                item.setCantidadActual(actual + mov.getCantidad());
            } else if ("SALIDA".equals(mov.getTipoMovimiento())) {
                item.setCantidadActual(actual - mov.getCantidad());
            }
            itemRepo.save(item);
        }
        return guardado;
    }

    @DeleteMapping("/items/{id}")
    public void eliminarItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
    }
}