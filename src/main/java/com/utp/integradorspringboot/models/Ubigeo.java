package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ubigeo")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Ubigeo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer codigo;

    @Column(nullable = false, length = 50)
    private String departamento;

    @Column(nullable = false, length = 50)
    private String provincia;

    @Column(nullable = false, length = 50)
    private String distrito;
}