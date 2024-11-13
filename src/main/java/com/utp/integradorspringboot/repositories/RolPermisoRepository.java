package com.utp.integradorspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.integradorspringboot.models.RolPermiso;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, com.utp.integradorspringboot.models.RolPermisoId> {
}