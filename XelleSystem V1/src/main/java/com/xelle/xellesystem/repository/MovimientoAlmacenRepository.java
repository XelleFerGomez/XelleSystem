package com.xelle.xellesystem.repository;
import com.xelle.xellesystem.model.MovimientoAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoAlmacenRepository extends JpaRepository<MovimientoAlmacen, Long> {}