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

import com.utp.integradorspringboot.models.Medico;
import com.utp.integradorspringboot.repositories.MedicoRepository;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Medico>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Medico> lista = medicoRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getById(@PathVariable("id") Integer id) {
        Optional<Medico> entidad = medicoRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody Medico entidad) {
        try {
            Medico _entidad = medicoRepository.save(new Medico(null, entidad.getNombre(), entidad.getApellidoPaterno(), entidad.getApellidoMaterno(), entidad.getEspecialidad(), entidad.getCMP(), entidad.getTelefono(), entidad.getSueldo(), entidad.getUsuario()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable("id") Integer id, @RequestBody Medico entidad) {
        Medico _entidad = medicoRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setNombre(entidad.getNombre());
            _entidad.setApellidoPaterno(entidad.getApellidoPaterno());
            _entidad.setApellidoMaterno(entidad.getApellidoMaterno());
            _entidad.setEspecialidad(entidad.getEspecialidad());
            _entidad.setCMP(entidad.getCMP());
            _entidad.setTelefono(entidad.getTelefono());
            _entidad.setSueldo(entidad.getSueldo());
            _entidad.setUsuario(entidad.getUsuario());
            return new ResponseEntity<>(medicoRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            medicoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping("/especialidad/{nombreEspecialidad}")
public ResponseEntity<List<Medico>> getMedicosPorEspecialidad(@PathVariable("nombreEspecialidad") String nombreEspecialidad) {
    try {
        List<Medico> lista = medicoRepository.findByEspecialidadNombre(nombreEspecialidad); 
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
