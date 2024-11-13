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

import com.utp.integradorspringboot.models.Reporte;
import com.utp.integradorspringboot.repositories.ReporteRepository;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping
    public ResponseEntity<List<Reporte>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Reporte> lista = reporteRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getById(@PathVariable("id") Integer id) {
        Optional<Reporte> entidad = reporteRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Reporte> create(@RequestBody Reporte entidad) {
        try {
            Reporte _entidad = reporteRepository.save(new Reporte(null, entidad.getUsuario(), entidad.getTipoReporte(), entidad.getDescripcion(), entidad.getFiltros(), entidad.getFechaGeneracion(), entidad.getHoraGeneracion()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> update(@PathVariable("id") Integer id, @RequestBody Reporte entidad) {
        Reporte _entidad = reporteRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setUsuario(entidad.getUsuario());
            _entidad.setTipoReporte(entidad.getTipoReporte());
            _entidad.setDescripcion(entidad.getDescripcion());
            _entidad.setFiltros(entidad.getFiltros());
            _entidad.setFechaGeneracion(entidad.getFechaGeneracion());
            _entidad.setHoraGeneracion(entidad.getHoraGeneracion());
            return new ResponseEntity<>(reporteRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            reporteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}