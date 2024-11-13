package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
}