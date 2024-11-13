package com.utp.integradorspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacientesController {

    @RequestMapping({"", "/citas"})
    public String citas() {
        return "pac.Citas";
    }

    @RequestMapping({"/cita/nuevacita", "/cita/edit-cita"})
    public String formCitas() {
        return "pac.formCitas";
    }

    @RequestMapping("/pagos")
    public String pagos() {
        return "pac.Pagos";
    }

    @RequestMapping("/recetas")
    public String recetas() {
        return "pac.Recetas";
    }

    @RequestMapping("/resultados")
    public String resultados() {
        return "pac.Resultados";
    }

}
