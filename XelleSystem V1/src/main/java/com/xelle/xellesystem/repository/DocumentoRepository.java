package com.xelle.xellesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xelle.xellesystem.model.DocumentoSGC;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<DocumentoSGC, Long> {
    List<DocumentoSGC> findByTipo(String tipo);
    List<DocumentoSGC> findByArea(String area);
}