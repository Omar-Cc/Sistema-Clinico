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

import com.utp.integradorspringboot.models.DetalleFactura;
import com.utp.integradorspringboot.repositories.DetalleFacturaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/detalle-facturas")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @GetMapping
    public ResponseEntity<List<DetalleFactura>> getAll(@RequestParam(required = false) String title) {
        try {
            List<DetalleFactura> lista = detalleFacturaRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getById(@PathVariable("id") Integer id) {
        Optional<DetalleFactura> entidad = detalleFacturaRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleFactura> create(@RequestBody DetalleFactura entidad) {
        try {
            DetalleFactura _entidad = detalleFacturaRepository.save(new DetalleFactura(null, entidad.getFactura(), entidad.getDescripcion(), entidad.getCantidad(), entidad.getPrecioUnitario(), entidad.getSubtotal()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> update(@PathVariable("id") Integer id, @RequestBody DetalleFactura entidad) {
        DetalleFactura _entidad = detalleFacturaRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setFactura(entidad.getFactura());
            _entidad.setDescripcion(entidad.getDescripcion());
            _entidad.setCantidad(entidad.getCantidad());
            _entidad.setPrecioUnitario(entidad.getPrecioUnitario());
            _entidad.setSubtotal(entidad.getSubtotal());
            return new ResponseEntity<>(detalleFacturaRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            detalleFacturaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}