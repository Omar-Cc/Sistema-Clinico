package com.utp.integradorspringboot.models;

import java.io.Serializable;

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
@Table(name = "costo_especialidades")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Costo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double costo;

    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
}