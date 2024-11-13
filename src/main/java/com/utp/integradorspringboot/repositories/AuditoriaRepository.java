package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
}