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

import com.utp.integradorspringboot.models.Muestra;
import com.utp.integradorspringboot.repositories.MuestraRepository;

@RestController
@RequestMapping("/api/muestras")
public class MuestraController {

    @Autowired
    private MuestraRepository muestraRepository;

    @GetMapping
    public ResponseEntity<List<Muestra>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Muestra> lista = muestraRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Muestra> getById(@PathVariable("id") Integer id) {
        Optional<Muestra> entidad = muestraRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Muestra> create(@RequestBody Muestra entidad) {
        try {
            Muestra _entidad = muestraRepository.save(new Muestra(null, entidad.getOrdenLaboratorio(), entidad.getTipoMuestra(), entidad.getFechaRecoleccion()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Muestra> update(@PathVariable("id") Integer id, @RequestBody Muestra entidad) {
        Muestra _entidad = muestraRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setOrdenLaboratorio(entidad.getOrdenLaboratorio());
            _entidad.setTipoMuestra(entidad.getTipoMuestra());
            _entidad.setFechaRecoleccion(entidad.getFechaRecoleccion());
            return new ResponseEntity<>(muestraRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            muestraRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}