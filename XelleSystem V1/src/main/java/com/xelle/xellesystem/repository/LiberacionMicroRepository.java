package com.xelle.xellesystem.repository;

import com.xelle.xellesystem.model.LiberacionMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiberacionMicroRepository extends JpaRepository<LiberacionMicro, Long> {
}