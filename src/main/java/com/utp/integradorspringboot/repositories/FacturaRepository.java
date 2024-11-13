package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}