package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
}