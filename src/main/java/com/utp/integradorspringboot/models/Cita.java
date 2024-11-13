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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "fecha_cita", nullable = false)
    private Date fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private Time horaCita;

    @Column(name = "motivo")
    private String motivo; 

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @Column(name = "fecha_registrada", nullable = false)
    private Date fechaRegistrada;

    @Column(name = "hora_registrada", nullable = false)
    private Time horaRegistrada;

}