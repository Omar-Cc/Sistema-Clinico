package com.utp.integradorspringboot.models;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ToString
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data             //getters and setters
public class ResultadoAPI {
    private String genero;
    private String id;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String fecha_nacimiento;
    private String nombre_completo;
    private String codigo_verificacion;
    
}
