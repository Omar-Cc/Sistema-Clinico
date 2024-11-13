package com.utp.integradorspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.integradorspringboot.models.Costo;

@Repository
public interface CostoRepository extends JpaRepository<Costo, Integer> {
}