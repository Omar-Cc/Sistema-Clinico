package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}