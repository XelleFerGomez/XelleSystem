package com.xelle.xellesystem.repository;

import com.xelle.xellesystem.model.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {

    @Query(value = "SELECT * FROM fo_lc_20_proceso ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Proceso> findLastRecord();

    Optional<Proceso> findByNoRegistro(String noRegistro);
}