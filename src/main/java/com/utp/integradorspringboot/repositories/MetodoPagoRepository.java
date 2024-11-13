package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
}