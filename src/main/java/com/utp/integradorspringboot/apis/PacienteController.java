package com.utp.integradorspringboot.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.integradorspringboot.models.Paciente;
import com.utp.integradorspringboot.repositories.PacienteRepository;
import com.utp.integradorspringboot.services.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Paciente> lista = pacienteRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable("id") Integer id) {
        Optional<Paciente> entidad = pacienteRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteService.crearPaciente(paciente);
            return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error al crear el paciente: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable("id") Integer id, @RequestBody Paciente entidad) {
        Paciente _entidad = pacienteRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            _entidad.setApellido(entidad.getApellido());
            _entidad.setFechaNacimiento(entidad.getFechaNacimiento());
            _entidad.setGenero(entidad.getGenero());
            _entidad.setDireccion(entidad.getDireccion());
            _entidad.setTelefono(entidad.getTelefono());
            _entidad.setUsuario(entidad.getUsuario());
            _entidad.setUbigeo(entidad.getUbigeo());
            return new ResponseEntity<>(pacienteRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            pacienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}