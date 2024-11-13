package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turnos")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Turno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "hora_inicio", nullable = false)
    private Time horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private Time horaFin;

    @Column(name = "dia_semana", nullable = false, length = 10)
    private String diaSemana;

    @Column(name = "duracion_turno", nullable = false)
    private Integer duracionTurno;
}