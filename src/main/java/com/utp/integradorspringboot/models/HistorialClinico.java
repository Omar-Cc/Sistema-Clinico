package com.utp.integradorspringboot.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historiales_clinico")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class HistorialClinico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_consulta", nullable = false)
    private Date fechaConsulta;

    @Column(name = "hora_consulta", nullable = false)
    private Time horaConsulta;

    private String descripcionConsulta;
    private String diagnostico;
    private String tratamiento;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;
}