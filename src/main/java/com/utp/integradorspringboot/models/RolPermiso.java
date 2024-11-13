package com.utp.integradorspringboot.models;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles_permisos")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class RolPermiso implements Serializable {
    @EmbeddedId
    private RolPermisoId id;

    @ManyToOne
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToOne
    @MapsId("idPermiso")
    @JoinColumn(name = "id_permiso")
    private Permiso permiso;
}