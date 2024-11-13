package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.OrdenLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenLaboratorioRepository extends JpaRepository<OrdenLaboratorio, Integer> {
}