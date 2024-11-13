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

import com.utp.integradorspringboot.models.MetodoPago;
import com.utp.integradorspringboot.repositories.MetodoPagoRepository;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> getAll(@RequestParam(required = false) String title) {
        try {
            List<MetodoPago> lista = metodoPagoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getById(@PathVariable("id") Integer id) {
        Optional<MetodoPago> entidad = metodoPagoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MetodoPago> create(@RequestBody MetodoPago entidad) {
        try {
            MetodoPago _entidad = metodoPagoRepository.save(new MetodoPago(null, entidad.getNombre()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> update(@PathVariable("id") Integer id, @RequestBody MetodoPago entidad) {
        MetodoPago _entidad = metodoPagoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            return new ResponseEntity<>(metodoPagoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            metodoPagoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}