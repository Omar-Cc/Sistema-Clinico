package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}