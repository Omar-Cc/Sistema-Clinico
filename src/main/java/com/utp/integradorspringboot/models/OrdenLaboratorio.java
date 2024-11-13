package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordenes_laboratorio")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class OrdenLaboratorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;
}