package com.utp.integradorspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utp.integradorspringboot.models.Paciente;
import com.utp.integradorspringboot.models.Ubigeo;
import com.utp.integradorspringboot.models.Usuario;
import com.utp.integradorspringboot.repositories.PacienteRepository;
import com.utp.integradorspringboot.repositories.UbigeoRepository;
import com.utp.integradorspringboot.repositories.UsuarioRepository;

@Service
public class PacienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UbigeoRepository ubigeoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crearPaciente(Paciente paciente) {
        // Verificar que el usuario existe
        Usuario usuario = usuarioRepository.findById(paciente.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("Usuario encontrado: " + usuario);

        // Verificar que el ubigeo existe
        Ubigeo ubigeo = ubigeoRepository.findById(paciente.getUbigeo().getId())
                .orElseThrow(() -> new RuntimeException("Ubigeo no encontrado"));
        System.out.println("Ubigeo encontrado: " + ubigeo);

        // Verificar y asignar la fecha de nacimiento
        if (paciente.getFechaNacimiento() == null) {
            throw new RuntimeException("Fecha de nacimiento no puede ser nula");
        }
        System.out.println("Fecha de nacimiento: " + paciente.getFechaNacimiento());

        // Asignar las entidades verificadas al paciente
        paciente.setUsuario(usuario);
        paciente.setUbigeo(ubigeo);

        // Guardar el paciente en la base de datos
        return pacienteRepository.save(paciente);
    }
}
