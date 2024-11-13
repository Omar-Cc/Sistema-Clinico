package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contactos_emergencia")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class ContactoEmergencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String relacion;

    @Column(nullable = false, length = 9)
    private String telefono;

    private String email;

    private String direccion;

    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;
}