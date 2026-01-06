package com.xelle.xellesystem.repository;
import com.xelle.xellesystem.model.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Long> {
}