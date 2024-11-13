package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "muestras")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Muestra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_orden_lab", nullable = false)
    private OrdenLaboratorio ordenLaboratorio;

    @Column(nullable = false, length = 50)
    private String tipoMuestra;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRecoleccion;
}