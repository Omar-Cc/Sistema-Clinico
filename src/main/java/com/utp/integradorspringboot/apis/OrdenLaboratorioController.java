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

import com.utp.integradorspringboot.models.OrdenLaboratorio;
import com.utp.integradorspringboot.repositories.OrdenLaboratorioRepository;

@RestController
@RequestMapping("/api/ordenes-laboratorio")
public class OrdenLaboratorioController {

    @Autowired
    private OrdenLaboratorioRepository ordenLaboratorioRepository;

    @GetMapping
    public ResponseEntity<List<OrdenLaboratorio>> getAll(@RequestParam(required = false) String title) {
        try {
            List<OrdenLaboratorio> lista = ordenLaboratorioRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenLaboratorio> getById(@PathVariable("id") Integer id) {
        Optional<OrdenLaboratorio> entidad = ordenLaboratorioRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrdenLaboratorio> create(@RequestBody OrdenLaboratorio entidad) {
        try {
            OrdenLaboratorio _entidad = ordenLaboratorioRepository.save(new OrdenLaboratorio(null, entidad.getPaciente(), entidad.getMedico(), entidad.getFechaOrden(), entidad.getTotal(), entidad.getCita()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenLaboratorio> update(@PathVariable("id") Integer id, @RequestBody OrdenLaboratorio entidad) {
        OrdenLaboratorio _entidad = ordenLaboratorioRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setPaciente(entidad.getPaciente());
            _entidad.setMedico(entidad.getMedico());
            _entidad.setFechaOrden(entidad.getFechaOrden());
            _entidad.setTotal(entidad.getTotal());
            _entidad.setCita(entidad.getCita());
            return new ResponseEntity<>(ordenLaboratorioRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            ordenLaboratorioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}