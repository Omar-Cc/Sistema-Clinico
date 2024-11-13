package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
}