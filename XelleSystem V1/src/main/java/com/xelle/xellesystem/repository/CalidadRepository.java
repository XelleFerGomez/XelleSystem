package com.xelle.xellesystem.repository;
import com.xelle.xellesystem.model.Calidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalidadRepository extends JpaRepository<Calidad, Long> {
}