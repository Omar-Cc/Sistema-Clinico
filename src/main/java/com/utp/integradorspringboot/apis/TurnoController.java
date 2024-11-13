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

import com.utp.integradorspringboot.models.Turno;
import com.utp.integradorspringboot.repositories.TurnoRepository;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping
    public ResponseEntity<List<Turno>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Turno> lista = turnoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getById(@PathVariable("id") Integer id) {
        Optional<Turno> entidad = turnoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Turno> create(@RequestBody Turno entidad) {
        try {
            Turno _entidad = turnoRepository.save(new Turno(null, entidad.getMedico(), entidad.getHoraInicio(), entidad.getHoraFin(), entidad.getDiaSemana(), entidad.getDuracionTurno()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> update(@PathVariable("id") Integer id, @RequestBody Turno entidad) {
        Turno _entidad = turnoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setMedico(entidad.getMedico());
            _entidad.setHoraInicio(entidad.getHoraInicio());
            _entidad.setHoraFin(entidad.getHoraFin());
            _entidad.setDiaSemana(entidad.getDiaSemana());
            _entidad.setDuracionTurno(entidad.getDuracionTurno());
            return new ResponseEntity<>(turnoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            turnoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}