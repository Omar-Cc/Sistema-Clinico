package com.utp.integradorspringboot.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.integradorspringboot.models.Especialidad;
import com.utp.integradorspringboot.repositories.EspecialidadRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @GetMapping
    public ResponseEntity<List<Especialidad>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Especialidad> lista = especialidadRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getById(@PathVariable("id") Integer id) {
        Optional<Especialidad> entidad = especialidadRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Especialidad> create(@RequestBody Especialidad entidad) {
        try {
            Especialidad _entidad = especialidadRepository.save(new Especialidad(null, entidad.getNombre(), entidad.getDescripcion()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> update(@PathVariable("id") Integer id, @RequestBody Especialidad entidad) {
        Especialidad _entidad = especialidadRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            _entidad.setDescripcion(entidad.getDescripcion());
            return new ResponseEntity<>(especialidadRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            especialidadRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}