package com.utp.integradorspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/medico")
@RequiredArgsConstructor
public class MedicosController {

    @RequestMapping({"", "/historiales-clinicos"})
    
    public String historialesClinicos() {
        return "med.HistorialesClinicos";
    }

    @RequestMapping("/agenda")
    public String agenda() {
        return "med.Agenda";
    }

    @RequestMapping("/citas/cita/{id}")
    public String cita() {
        return "med.Cita";
    }

    @RequestMapping("/citas")
    public String citas() {
        return "med.Citas";
    }

    @RequestMapping({"/historial-clinico", "/pacientes"})
    public String historialClinico() {
        return "med.HistorialClinico";
    }

    @RequestMapping("/receta-medica")
    public String recetaMedica() {
        return "med.RecetaMedica";
    }
}
