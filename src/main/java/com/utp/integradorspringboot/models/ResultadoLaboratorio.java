package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resultados_laboratorio")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class ResultadoLaboratorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_orden_lab", nullable = false)
    private OrdenLaboratorio ordenLaboratorio;

    @ManyToOne
    @JoinColumn(name = "id_prueba", nullable = false)
    private PruebaLaboratorio pruebaLaboratorio;

    private String resultado;
    private String unidadMedida;

    @Column(nullable = false)
    private Date fechaResultado;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @Column(nullable = false)
    private Double progreso;
}