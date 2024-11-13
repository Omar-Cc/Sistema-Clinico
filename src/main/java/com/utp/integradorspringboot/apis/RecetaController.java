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

import com.utp.integradorspringboot.models.Receta;
import com.utp.integradorspringboot.repositories.RecetaRepository;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaRepository recetaRepository;

    @GetMapping
    public ResponseEntity<List<Receta>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Receta> lista = recetaRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getById(@PathVariable("id") Integer id) {
        Optional<Receta> entidad = recetaRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Receta> create(@RequestBody Receta entidad) {
        try {
            Receta _entidad = recetaRepository.save(new Receta(null, entidad.getPaciente(), entidad.getMedico(), entidad.getCita(), entidad.getFechaPrescripcion(), entidad.getFechaVencimiento()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> update(@PathVariable("id") Integer id, @RequestBody Receta entidad) {
        Receta _entidad = recetaRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setPaciente(entidad.getPaciente());
            _entidad.setMedico(entidad.getMedico());
            _entidad.setCita(entidad.getCita());
            _entidad.setFechaPrescripcion(entidad.getFechaPrescripcion());
            _entidad.setFechaVencimiento(entidad.getFechaVencimiento());
            return new ResponseEntity<>(recetaRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            recetaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}