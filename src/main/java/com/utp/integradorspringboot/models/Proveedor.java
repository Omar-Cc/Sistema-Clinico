package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String nombre;

    private String direccion;

    @Column(length = 9)
    private String telefono;

    @Column(length = 50)
    private String email;
}