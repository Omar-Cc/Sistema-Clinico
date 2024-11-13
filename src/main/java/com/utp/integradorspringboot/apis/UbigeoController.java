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

import com.utp.integradorspringboot.models.Ubigeo;
import com.utp.integradorspringboot.repositories.UbigeoRepository;

@RestController
@RequestMapping("/api/ubigeos")
public class UbigeoController {

    @Autowired
    private UbigeoRepository ubigeoRepository;

    @GetMapping
    public ResponseEntity<List<Ubigeo>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Ubigeo> lista = ubigeoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubigeo> getById(@PathVariable("id") Integer id) {
        Optional<Ubigeo> entidad = ubigeoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ubigeo> create(@RequestBody Ubigeo entidad) {
        try {
            Ubigeo _entidad = ubigeoRepository.save(new Ubigeo(null, entidad.getCodigo(), entidad.getDepartamento(), entidad.getProvincia(), entidad.getDistrito()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubigeo> update(@PathVariable("id") Integer id, @RequestBody Ubigeo entidad) {
        Ubigeo _entidad = ubigeoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setCodigo(entidad.getCodigo());
            _entidad.setDepartamento(entidad.getDepartamento());
            _entidad.setProvincia(entidad.getProvincia());
            _entidad.setDistrito(entidad.getDistrito());
            return new ResponseEntity<>(ubigeoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            ubigeoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}