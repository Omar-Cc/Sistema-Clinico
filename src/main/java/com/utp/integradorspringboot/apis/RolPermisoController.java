package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.RolPermiso;
import com.utp.integradorspringboot.models.RolPermisoId;
import com.utp.integradorspringboot.repositories.RolPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles-permisos")
public class RolPermisoController {

    @Autowired
    private RolPermisoRepository rolPermisoRepository;

    @GetMapping
    public ResponseEntity<List<RolPermiso>> getAll(@RequestParam(required = false) String title) {
        try {
            List<RolPermiso> lista = rolPermisoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolPermiso> getById(@PathVariable RolPermisoId id) {
        Optional<RolPermiso> entidad = rolPermisoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RolPermiso> create(@RequestBody RolPermiso entidad) {
        try {
            RolPermiso _entidad = rolPermisoRepository.save(new RolPermiso(entidad.getId(), entidad.getRol(), entidad.getPermiso()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolPermiso> update(@PathVariable RolPermisoId id, @RequestBody RolPermiso entidad) {
        RolPermiso _entidad = rolPermisoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setRol(entidad.getRol());
            _entidad.setPermiso(entidad.getPermiso());
            return new ResponseEntity<>(rolPermisoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable RolPermisoId id) {
        try {
            rolPermisoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}