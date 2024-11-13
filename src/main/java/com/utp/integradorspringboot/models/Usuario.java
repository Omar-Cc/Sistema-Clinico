package com.utp.integradorspringboot.models;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 9, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private java.util.Date fechaCreacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id")
    )
    private Collection<Rol> roles;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", documento='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}