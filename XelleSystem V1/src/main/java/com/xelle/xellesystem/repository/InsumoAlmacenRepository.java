package com.xelle.xellesystem.repository;
import com.xelle.xellesystem.model.InsumoAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoAlmacenRepository extends JpaRepository<InsumoAlmacen, Long> {}