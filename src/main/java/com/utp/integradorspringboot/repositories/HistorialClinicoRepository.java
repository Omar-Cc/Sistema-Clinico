package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Integer> {
}