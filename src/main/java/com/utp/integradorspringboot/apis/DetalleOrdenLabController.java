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

import com.utp.integradorspringboot.models.DetalleOrdenLab;
import com.utp.integradorspringboot.repositories.DetalleOrdenLabRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/detalle-orden-labs")
public class DetalleOrdenLabController {

    @Autowired
    private DetalleOrdenLabRepository detalleOrdenLabRepository;

    @GetMapping
    public ResponseEntity<List<DetalleOrdenLab>> getAll(@RequestParam(required = false) String title) {
        try {
            List<DetalleOrdenLab> lista = detalleOrdenLabRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenLab> getById(@PathVariable("id") Integer id) {
        Optional<DetalleOrdenLab> entidad = detalleOrdenLabRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleOrdenLab> create(@RequestBody DetalleOrdenLab entidad) {
        try {
            DetalleOrdenLab _entidad = detalleOrdenLabRepository.save(new DetalleOrdenLab(null, entidad.getOrdenLaboratorio(), entidad.getPruebaLaboratorio(), entidad.getEstado(), entidad.getSubtotal(), entidad.getProgreso()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrdenLab> update(@PathVariable("id") Integer id, @RequestBody DetalleOrdenLab entidad) {
        DetalleOrdenLab _entidad = detalleOrdenLabRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setOrdenLaboratorio(entidad.getOrdenLaboratorio());
            _entidad.setPruebaLaboratorio(entidad.getPruebaLaboratorio());
            _entidad.setEstado(entidad.getEstado());
            _entidad.setSubtotal(entidad.getSubtotal());
            _entidad.setProgreso(entidad.getProgreso());
            return new ResponseEntity<>(detalleOrdenLabRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            detalleOrdenLabRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}