package com.utp.integradorspringboot.dto;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitaDTO {
    private Integer id;
    private String paciente;
    private String documento;
    private Date fechaCita;
    private Time horaCita;
    
    private String medico;
    private String especialidad;
    private String estado;

    public CitaDTO(Integer id, String paciente, String documento, Date fechaCita, Time horaCita) {
        this.id = id;
        this.paciente = paciente;
        this.documento = documento;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
    }

    public CitaDTO(String medico, String especialidad, Date fechaCita, Time horaCita, String estado) {
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.medico = medico;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    //private String documento;
    private String telefono;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String motivo;
    private String correo;

    
    public CitaDTO(Integer id, String documento, String telefono, String nombre, String apellido, Date fechaNacimiento, String motivo, String correo) {
        this.id = id;
        this.documento = documento;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.motivo = motivo;
        this.correo = correo;
    }

}
