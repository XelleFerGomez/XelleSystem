package com.xelle.xellesystem.repository;

import com.xelle.xellesystem.model.BitacoraCultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BitacoraRepository extends JpaRepository<BitacoraCultivo, Long> {
    
    // Método extra útil para buscar por folio
    Optional<BitacoraCultivo> findByNoRegistro(String noRegistro);
}