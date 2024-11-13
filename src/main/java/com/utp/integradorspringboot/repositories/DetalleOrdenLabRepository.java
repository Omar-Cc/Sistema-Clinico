package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.DetalleOrdenLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenLabRepository extends JpaRepository<DetalleOrdenLab, Integer> {
}