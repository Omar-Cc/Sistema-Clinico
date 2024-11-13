package com.utp.integradorspringboot.models;

import lombok.Data;

@Data
public class DatosVerificacion {
    private String documento;
    private String fecha_nacimiento;
    private String codigoVerificacion;
}
