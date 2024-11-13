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

import com.utp.integradorspringboot.models.Permiso;
import com.utp.integradorspringboot.repositories.PermisoRepository;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping
    public ResponseEntity<List<Permiso>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Permiso> lista = permisoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> getById(@PathVariable("id") Integer id) {
        Optional<Permiso> entidad = permisoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Permiso> create(@RequestBody Permiso entidad) {
        try {
            Permiso _entidad = permisoRepository.save(new Permiso(null, entidad.getNombrePermiso(), entidad.getDescripcion()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permiso> update(@PathVariable("id") Integer id, @RequestBody Permiso entidad) {
        Permiso _entidad = permisoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombrePermiso(entidad.getNombrePermiso());
            _entidad.setDescripcion(entidad.getDescripcion());
            return new ResponseEntity<>(permisoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            permisoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}