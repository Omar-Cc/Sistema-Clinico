package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.ResultadoLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoLaboratorioRepository extends JpaRepository<ResultadoLaboratorio, Integer> {
}