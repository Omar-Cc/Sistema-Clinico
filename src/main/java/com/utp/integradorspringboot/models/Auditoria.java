package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auditorias")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Auditoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String accion;

    @Column(name = "tabla_afectada", nullable = false, length = 50)
    private String tablaAfectada;

    @Column(name = "id_registro_afectado", nullable = false)
    private Integer idRegistroAfectado;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Time hora;

    private String descripcion;
}