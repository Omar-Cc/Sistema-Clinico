package com.utp.integradorspringboot.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.integradorspringboot.models.Usuario;
import com.utp.integradorspringboot.services.LoginService;

@RestController
@RequestMapping("/api")
public class LoginsController {
    
    @Autowired
    private LoginService loginService;

    @PostMapping("/logins")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        Usuario user = loginService.login(usuario.getUsername(), usuario.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}