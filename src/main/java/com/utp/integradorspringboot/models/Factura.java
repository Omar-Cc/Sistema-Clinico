package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facturas")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Column(name = "fecha_factura", nullable = false)
    private Date fechaFactura;

    @Column(name = "hora_factura", nullable = false)
    private Time horaFactura;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;
}