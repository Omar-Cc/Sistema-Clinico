package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personales")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Personal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 25)
    private String apellidoP;

    @Column(nullable = false, length = 25)
    private String apellidoM;

    @Column(length = 9, unique = true)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}