package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}