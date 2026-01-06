package com.xelle.xellesystem.repository;
import com.xelle.xellesystem.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {}