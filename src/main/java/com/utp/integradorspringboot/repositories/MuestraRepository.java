package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Muestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Integer> {
}