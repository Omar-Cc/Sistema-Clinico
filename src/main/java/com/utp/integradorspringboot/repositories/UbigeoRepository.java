package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo, Integer> {
}