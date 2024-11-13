package com.utp.integradorspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.integradorspringboot.models.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    
}