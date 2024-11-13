package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportes")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Reporte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "tipo_reporte", nullable = false, length = 30)
    private String tipoReporte;

    private String descripcion;
    private String filtros;

    @Column(name = "fecha_generacion", nullable = false)
    private Date fechaGeneracion;

    @Column(name = "hora_generacion", nullable = false)
    private Time horaGeneracion;
}