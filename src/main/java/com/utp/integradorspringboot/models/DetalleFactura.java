package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "factura_detalles")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    private String descripcion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;
}