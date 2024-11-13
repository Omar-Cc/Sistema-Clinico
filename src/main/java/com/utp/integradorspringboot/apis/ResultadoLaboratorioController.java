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

import com.utp.integradorspringboot.models.ResultadoLaboratorio;
import com.utp.integradorspringboot.repositories.ResultadoLaboratorioRepository;

@RestController
@RequestMapping("/api/resultados-laboratorio")
public class ResultadoLaboratorioController {

    @Autowired
    private ResultadoLaboratorioRepository resultadoLaboratorioRepository;

    @GetMapping
    public ResponseEntity<List<ResultadoLaboratorio>> getAll(@RequestParam(required = false) String title) {
        try {
            List<ResultadoLaboratorio> lista = resultadoLaboratorioRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoLaboratorio> getById(@PathVariable("id") Integer id) {
        Optional<ResultadoLaboratorio> entidad = resultadoLaboratorioRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ResultadoLaboratorio> create(@RequestBody ResultadoLaboratorio entidad) {
        try {
            ResultadoLaboratorio _entidad = resultadoLaboratorioRepository.save(new ResultadoLaboratorio(null, entidad.getOrdenLaboratorio(), entidad.getPruebaLaboratorio(), entidad.getResultado(), entidad.getUnidadMedida(), entidad.getFechaResultado(), entidad.getEstado(), entidad.getProgreso()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoLaboratorio> update(@PathVariable("id") Integer id, @RequestBody ResultadoLaboratorio entidad) {
        ResultadoLaboratorio _entidad = resultadoLaboratorioRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setOrdenLaboratorio(entidad.getOrdenLaboratorio());
            _entidad.setPruebaLaboratorio(entidad.getPruebaLaboratorio());
            _entidad.setResultado(entidad.getResultado());
            _entidad.setUnidadMedida(entidad.getUnidadMedida());
            _entidad.setFechaResultado(entidad.getFechaResultado());
            _entidad.setEstado(entidad.getEstado());
            _entidad.setProgreso(entidad.getProgreso());
            return new ResponseEntity<>(resultadoLaboratorioRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            resultadoLaboratorioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}