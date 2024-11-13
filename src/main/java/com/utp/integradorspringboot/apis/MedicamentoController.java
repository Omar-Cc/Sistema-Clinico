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

import com.utp.integradorspringboot.models.Medicamento;
import com.utp.integradorspringboot.repositories.MedicamentoRepository;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @GetMapping
    public ResponseEntity<List<Medicamento>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Medicamento> lista = medicamentoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getById(@PathVariable("id") Integer id) {
        Optional<Medicamento> entidad = medicamentoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Medicamento> create(@RequestBody Medicamento entidad) {
        try {
            Medicamento _entidad = medicamentoRepository.save(new Medicamento(null, entidad.getNombre(), entidad.getDescripcion(), entidad.getCategoria(), entidad.getPrecio(), entidad.getStock(), entidad.getFechaVencimiento(), entidad.getProveedor()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable("id") Integer id, @RequestBody Medicamento entidad) {
        Medicamento _entidad = medicamentoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            _entidad.setDescripcion(entidad.getDescripcion());
            _entidad.setCategoria(entidad.getCategoria());
            _entidad.setPrecio(entidad.getPrecio());
            _entidad.setStock(entidad.getStock());
            _entidad.setFechaVencimiento(entidad.getFechaVencimiento());
            _entidad.setProveedor(entidad.getProveedor());
            return new ResponseEntity<>(medicamentoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            medicamentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}