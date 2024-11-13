package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.DetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Integer> {
}