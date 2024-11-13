package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receta_detalles")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class DetalleReceta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamento medicamento;
    
    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, length = 50)
    private String dosis;

    @Column(nullable = false, length = 50)
    private String via;

    @Column(length = 50)
    private String frecuencia;

    @Column(length = 50)
    private String duracion;
}