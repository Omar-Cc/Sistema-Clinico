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

import com.utp.integradorspringboot.models.Personal;
import com.utp.integradorspringboot.repositories.PersonalRepository;

@RestController
@RequestMapping("/api/personales")
public class PersonalController {

    @Autowired
    private PersonalRepository personalRepository;

    @GetMapping
    public ResponseEntity<List<Personal>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Personal> lista = personalRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getById(@PathVariable("id") Integer id) {
        Optional<Personal> entidad = personalRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Personal> create(@RequestBody Personal entidad) {
        try {
            Personal _entidad = personalRepository.save(new Personal(null, entidad.getNombre(), entidad.getApellidoP(), entidad.getApellidoM(), entidad.getTelefono(), entidad.getUsuario()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> update(@PathVariable("id") Integer id, @RequestBody Personal entidad) {
        Personal _entidad = personalRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            _entidad.setApellidoP(entidad.getApellidoP());
            _entidad.setApellidoM(entidad.getApellidoM());
            _entidad.setTelefono(entidad.getTelefono());
            _entidad.setUsuario(entidad.getUsuario());
            return new ResponseEntity<>(personalRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            personalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}