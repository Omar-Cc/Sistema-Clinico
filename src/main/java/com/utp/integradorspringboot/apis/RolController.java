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

import com.utp.integradorspringboot.models.Rol;
import com.utp.integradorspringboot.repositories.RolRepository;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<List<Rol>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Rol> lista = rolRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable("id") Integer id) {
        Optional<Rol> entidad = rolRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol entidad) {
        try {
            Rol _entidad = rolRepository.save(
                new Rol(
                    null, 
                    entidad.getNombre(), 
                    entidad.getDescripcion(), 
                    entidad.getUsuarios()
                    ));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable("id") Integer id, @RequestBody Rol entidad) {
        Rol _entidad = rolRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            _entidad.setDescripcion(entidad.getDescripcion());
            _entidad.setUsuarios(entidad.getUsuarios());
            return new ResponseEntity<>(rolRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            rolRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}