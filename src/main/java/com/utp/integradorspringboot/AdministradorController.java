package com.utp.integradorspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdministradorController {
    
    @GetMapping({"", "/usuarios"})
    public String usuarios() {
        return "adm.Usuarios";
    }

    @GetMapping("/permisos")
    public String permisos() {
        return "adm.Permisos";
    }

    @GetMapping("/reportes")
    public String reportes() {
        return "adm.Reportes";
    }

    @GetMapping("/roles")
    public String roles() {
        return "adm.Roles";
    }
}
