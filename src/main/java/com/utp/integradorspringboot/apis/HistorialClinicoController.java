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

import com.utp.integradorspringboot.models.HistorialClinico;
import com.utp.integradorspringboot.repositories.HistorialClinicoRepository;

@RestController
@RequestMapping("/api/historial-clinicos")
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @GetMapping
    public ResponseEntity<List<HistorialClinico>> getAll(@RequestParam(required = false) String title) {
        try {
            List<HistorialClinico> lista = historialClinicoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> getById(@PathVariable("id") Integer id) {
        Optional<HistorialClinico> entidad = historialClinicoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HistorialClinico> create(@RequestBody HistorialClinico entidad) {
        try {
            HistorialClinico _entidad = historialClinicoRepository.save(new HistorialClinico(null, entidad.getPaciente(), entidad.getFechaConsulta(), entidad.getHoraConsulta(),entidad.getDescripcionConsulta(), entidad.getDiagnostico(), entidad.getTratamiento(), entidad.getCita()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialClinico> update(@PathVariable("id") Integer id, @RequestBody HistorialClinico entidad) {
        HistorialClinico _entidad = historialClinicoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setPaciente(entidad.getPaciente());
            _entidad.setFechaConsulta(entidad.getFechaConsulta());
            _entidad.setHoraConsulta(entidad.getHoraConsulta());
            _entidad.setDescripcionConsulta(entidad.getDescripcionConsulta());
            _entidad.setDiagnostico(entidad.getDiagnostico());
            _entidad.setTratamiento(entidad.getTratamiento());
            _entidad.setCita(entidad.getCita());
            return new ResponseEntity<>(historialClinicoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            historialClinicoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}