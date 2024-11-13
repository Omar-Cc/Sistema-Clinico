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

import com.utp.integradorspringboot.models.DetalleOrden;
import com.utp.integradorspringboot.repositories.DetalleOrdenRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/detalle-ordenes")
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @GetMapping
    public ResponseEntity<List<DetalleOrden>> getAll(@RequestParam(required = false) String title) {
        try {
            List<DetalleOrden> lista = detalleOrdenRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrden> getById(@PathVariable("id") Integer id) {
        Optional<DetalleOrden> entidad = detalleOrdenRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleOrden> create(@RequestBody DetalleOrden entidad) {
        try {
            DetalleOrden _entidad = detalleOrdenRepository.save(new DetalleOrden(null, entidad.getOrden(), entidad.getMedicamento(), entidad.getCantidad(), entidad.getPrecioUnitario(), entidad.getSubtotal()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrden> update(@PathVariable("id") Integer id, @RequestBody DetalleOrden entidad) {
        DetalleOrden _entidad = detalleOrdenRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setOrden(entidad.getOrden());
            _entidad.setMedicamento(entidad.getMedicamento());
            _entidad.setCantidad(entidad.getCantidad());
            _entidad.setPrecioUnitario(entidad.getPrecioUnitario());
            _entidad.setSubtotal(entidad.getSubtotal());
            return new ResponseEntity<>(detalleOrdenRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            detalleOrdenRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}