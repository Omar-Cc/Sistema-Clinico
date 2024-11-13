package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Recetas")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Receta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;

    @Column(name = "fecha_prescripcion", nullable = false)
    private Date fechaPrescripcion;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Date fechaVencimiento;
}