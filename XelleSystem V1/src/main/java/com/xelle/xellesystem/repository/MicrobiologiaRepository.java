package com.xelle.xellesystem.repository;

import com.xelle.xellesystem.model.Microbiologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicrobiologiaRepository extends JpaRepository<Microbiologia, Long> {
}