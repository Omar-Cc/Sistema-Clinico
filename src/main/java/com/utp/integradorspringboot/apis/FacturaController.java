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

import com.utp.integradorspringboot.models.Factura;
import com.utp.integradorspringboot.repositories.FacturaRepository;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @GetMapping
    public ResponseEntity<List<Factura>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Factura> lista = facturaRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getById(@PathVariable("id") Integer id) {
        Optional<Factura> entidad = facturaRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Factura> create(@RequestBody Factura entidad) {
        try {
            Factura _entidad = facturaRepository.save(new Factura(null, entidad.getPaciente(), entidad.getFechaFactura(), entidad.getHoraFactura(), entidad.getTotal(), entidad.getEstado()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> update(@PathVariable("id") Integer id, @RequestBody Factura entidad) {
        Factura _entidad = facturaRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setPaciente(entidad.getPaciente());
            _entidad.setFechaFactura(entidad.getFechaFactura());
            _entidad.setHoraFactura(entidad.getHoraFactura());
            _entidad.setTotal(entidad.getTotal());
            _entidad.setEstado(entidad.getEstado());
            return new ResponseEntity<>(facturaRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            facturaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}