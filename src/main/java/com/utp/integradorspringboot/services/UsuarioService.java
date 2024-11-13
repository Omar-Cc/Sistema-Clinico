package com.utp.integradorspringboot.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.integradorspringboot.models.Rol;
import com.utp.integradorspringboot.models.Usuario;
import com.utp.integradorspringboot.repositories.RolRepository;
import com.utp.integradorspringboot.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        try {
            // Codificar la contraseña del usuario
            String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());

            // Obtener el rol "Paciente"
            Rol rolPaciente = rolRepository.findByNombre("Paciente");
            if (rolPaciente == null) {
                throw new RuntimeException("Rol 'Paciente' no encontrado");
            }

            // Asignar la contraseña codificada y el rol al usuario
            usuario.setPassword(encodedPassword);
            usuario.setRoles(Set.of(rolPaciente));

            // Guardar el usuario
            Usuario nuevoUsuario = usuarioRepository.save(usuario);

            return nuevoUsuario;
        } catch (Exception e) {
            // Agregar registro de error
            System.err.println("Error al registrar usuario: " + e.getMessage());
            throw e;
        }
    }

    public List<String> getRolesByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            return usuario.getRoles().stream()
                    .map(Rol::getNombre)
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }
}