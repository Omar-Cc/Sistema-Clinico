package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden_lab_detalles")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class DetalleOrdenLab implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_orden_lab", nullable = false)
    private OrdenLaboratorio ordenLaboratorio;

    @ManyToOne
    @JoinColumn(name = "id_prueba", nullable = false)
    private PruebaLaboratorio pruebaLaboratorio;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double progreso;
}