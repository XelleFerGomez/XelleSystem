package com.xelle.xellesystem.repository;

import com.xelle.xellesystem.model.Dosificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosificacionRepository extends JpaRepository<Dosificacion, Long> {
}