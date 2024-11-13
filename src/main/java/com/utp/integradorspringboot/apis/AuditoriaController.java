package com.utp.integradorspringboot.apis;

import java.util.ArrayList;
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

import com.utp.integradorspringboot.models.Auditoria;
import com.utp.integradorspringboot.repositories.AuditoriaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auditorias")
public class AuditoriaController {

    @Autowired
    AuditoriaRepository repository;

    @GetMapping
    public ResponseEntity<List<Auditoria>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Auditoria> lista = new ArrayList<>();
            repository.findAll().forEach(lista::add);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auditoria> getById(@PathVariable("id") Integer id) {
        Optional<Auditoria> entidad = repository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Auditoria> create(@RequestBody Auditoria entidad) {
        try {
            Auditoria _entidad = repository.save(new Auditoria(null, entidad.getUsuario(), entidad.getAccion(),
                    entidad.getTablaAfectada(), entidad.getIdRegistroAfectado(), entidad.getFecha(), entidad.getHora(),
                    entidad.getDescripcion()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auditoria> update(@PathVariable("id") Integer id, @RequestBody Auditoria entidad) {
        Auditoria _entidad = repository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setUsuario(entidad.getUsuario());
            _entidad.setAccion(entidad.getAccion());
            _entidad.setTablaAfectada(entidad.getTablaAfectada());
            _entidad.setIdRegistroAfectado(entidad.getIdRegistroAfectado());
            _entidad.setFecha(entidad.getFecha());
            _entidad.setHora(entidad.getHora());
            _entidad.setDescripcion(entidad.getDescripcion());
            return new ResponseEntity<>(repository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuditoria(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}