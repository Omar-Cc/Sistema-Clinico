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

import com.utp.integradorspringboot.models.Pago;
import com.utp.integradorspringboot.repositories.PagoRepository;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @GetMapping
    public ResponseEntity<List<Pago>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Pago> lista = pagoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getById(@PathVariable("id") Integer id) {
        Optional<Pago> entidad = pagoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pago> create(@RequestBody Pago entidad) {
        try {
            Pago _entidad = pagoRepository.save(new Pago(null, entidad.getFactura(), entidad.getMetodoPago(), entidad.getFechaPago(), entidad.getHoraPago(), entidad.getMonto()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable("id") Integer id, @RequestBody Pago entidad) {
        Pago _entidad = pagoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setFactura(entidad.getFactura());
            _entidad.setMetodoPago(entidad.getMetodoPago());
            _entidad.setFechaPago(entidad.getFechaPago());
            _entidad.setHoraPago(entidad.getHoraPago());
            _entidad.setMonto(entidad.getMonto());
            return new ResponseEntity<>(pagoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            pagoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}