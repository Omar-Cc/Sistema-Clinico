package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.PruebaLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaLaboratorioRepository extends JpaRepository<PruebaLaboratorio, Integer> {
}