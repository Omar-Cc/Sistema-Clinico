package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicamentos")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Medicamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;
}