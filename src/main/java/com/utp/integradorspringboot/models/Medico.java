package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Medico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido_paterno",nullable = false, length = 30)
    private String apellidoPaterno;

    @Column(name = "apellido_materno",nullable = false, length = 30)
    private String apellidoMaterno;

    @ManyToOne
    @JoinColumn(name = "id_especialidad", nullable = false)
    private Especialidad especialidad;

    @Column(nullable = false, length = 50)
    private String CMP;

    @Column(length = 9, unique = true)
    private String telefono;

    private Double sueldo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}