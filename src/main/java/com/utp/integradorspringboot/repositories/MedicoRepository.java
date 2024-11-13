package com.utp.integradorspringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.integradorspringboot.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    List<Medico> findByEspecialidadNombre(String nombre);
}