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

import com.utp.integradorspringboot.models.DetalleReceta;
import com.utp.integradorspringboot.repositories.DetalleRecetaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/detalle-recetas")
public class DetalleRecetaController {

    @Autowired
    private DetalleRecetaRepository detalleRecetaRepository;

    @GetMapping
    public ResponseEntity<List<DetalleReceta>> getAll(@RequestParam(required = false) String title) {
        try {
            List<DetalleReceta> lista = detalleRecetaRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleReceta> getById(@PathVariable("id") Integer id) {
        Optional<DetalleReceta> entidad = detalleRecetaRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleReceta> create(@RequestBody DetalleReceta entidad) {
        try {
            DetalleReceta _entidad = detalleRecetaRepository.save(new DetalleReceta(null, entidad.getReceta(), entidad.getMedicamento(), entidad.getCantidad(), entidad.getDosis(), entidad.getVia(), entidad.getFrecuencia(), entidad.getDuracion()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleReceta> update(@PathVariable("id") Integer id, @RequestBody DetalleReceta entidad) {
        DetalleReceta _entidad = detalleRecetaRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setReceta(entidad.getReceta());
            _entidad.setMedicamento(entidad.getMedicamento());
            _entidad.setCantidad(entidad.getCantidad());
            _entidad.setDosis(entidad.getDosis());
            _entidad.setVia(entidad.getVia());
            _entidad.setFrecuencia(entidad.getFrecuencia());
            _entidad.setDuracion(entidad.getDuracion());
            return new ResponseEntity<>(detalleRecetaRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            detalleRecetaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}