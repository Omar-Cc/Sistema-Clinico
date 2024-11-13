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

import com.utp.integradorspringboot.models.Orden;
import com.utp.integradorspringboot.repositories.OrdenRepository;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping
    public ResponseEntity<List<Orden>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Orden> lista = ordenRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getById(@PathVariable("id") Integer id) {
        Optional<Orden> entidad = ordenRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Orden> create(@RequestBody Orden entidad) {
        try {
            Orden _entidad = ordenRepository.save(new Orden(null, entidad.getPaciente(), entidad.getMedico(), entidad.getFechaOrden(), entidad.getTotal(), entidad.getReceta()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> update(@PathVariable("id") Integer id, @RequestBody Orden entidad) {
        Orden _entidad = ordenRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setPaciente(entidad.getPaciente());
            _entidad.setMedico(entidad.getMedico());
            _entidad.setFechaOrden(entidad.getFechaOrden());
            _entidad.setTotal(entidad.getTotal());
            _entidad.setReceta(entidad.getReceta());
            return new ResponseEntity<>(ordenRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            ordenRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}