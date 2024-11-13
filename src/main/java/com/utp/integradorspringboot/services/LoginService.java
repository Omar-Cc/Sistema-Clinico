package com.utp.integradorspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.integradorspringboot.models.Usuario;
import com.utp.integradorspringboot.repositories.UsuarioRepository;

@Service
public class LoginService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario login(String username, String password) {
        // Lógica para obtener el usuario de la base de datos y verificar la contraseña
        Usuario user = usuarioRepository.findByUsername(username);
        if (user != null) {
            // Intentar verificar la contraseña encriptada
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
            // Si la verificación encriptada falla, intentar verificar como texto plano
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
