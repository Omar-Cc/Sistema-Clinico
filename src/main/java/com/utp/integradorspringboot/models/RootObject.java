package com.utp.integradorspringboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class RootObject {
    private boolean estado;
    private String mensaje;
    private ResultadoAPI resultado;

    
}
