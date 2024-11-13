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

import com.utp.integradorspringboot.models.PruebaLaboratorio;
import com.utp.integradorspringboot.repositories.PruebaLaboratorioRepository;

@RestController
@RequestMapping("/api/pruebas-laboratorio")
public class PruebaLaboratorioController {

    @Autowired
    private PruebaLaboratorioRepository pruebaLaboratorioRepository;

    @GetMapping
    public ResponseEntity<List<PruebaLaboratorio>> getAll(@RequestParam(required = false) String title) {
        try {
            List<PruebaLaboratorio> lista = pruebaLaboratorioRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaLaboratorio> getById(@PathVariable("id") Integer id) {
        Optional<PruebaLaboratorio> entidad = pruebaLaboratorioRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PruebaLaboratorio> create(@RequestBody PruebaLaboratorio entidad) {
        try {
            PruebaLaboratorio _entidad = pruebaLaboratorioRepository.save(new PruebaLaboratorio(null, entidad.getNombrePrueba(), entidad.getDescripcion(), entidad.getCosto()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PruebaLaboratorio> update(@PathVariable("id") Integer id, @RequestBody PruebaLaboratorio entidad) {
        PruebaLaboratorio _entidad = pruebaLaboratorioRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombrePrueba(entidad.getNombrePrueba());
            _entidad.setDescripcion(entidad.getDescripcion());
            _entidad.setCosto(entidad.getCosto());
            return new ResponseEntity<>(pruebaLaboratorioRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            pruebaLaboratorioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}