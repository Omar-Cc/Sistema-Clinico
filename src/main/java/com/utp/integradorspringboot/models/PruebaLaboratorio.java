package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pruebas_laboratorio")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class PruebaLaboratorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_prueba", nullable = false, length = 75)
    private String nombrePrueba;

    private String descripcion;

    @Column(nullable = false)
    private Double costo;
}