package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.ContactoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoEmergenciaRepository extends JpaRepository<ContactoEmergencia, Integer> {
}