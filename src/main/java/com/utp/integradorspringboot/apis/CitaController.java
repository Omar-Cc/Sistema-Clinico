package com.utp.integradorspringboot.apis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.utp.integradorspringboot.dto.CitaDTO;
import com.utp.integradorspringboot.models.Cita;
import com.utp.integradorspringboot.repositories.CitaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public ResponseEntity<List<Cita>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Cita> lista = citaRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medico")
    public List<CitaDTO> getAllCitas() {
        return citaRepository.findAll().stream()
                .map(cita -> new CitaDTO(
                        cita.getId(),
                        cita.getPaciente().getNombre(),
                        cita.getPaciente().getDocumento(),
                        cita.getFechaCita(),
                        cita.getHoraCita()))
                .collect(Collectors.toList());
    }

    @GetMapping("/paciente")
    public List<CitaDTO> getAllCitas2() {
        return citaRepository.findAll().stream()
                .map(cita -> new CitaDTO(
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad().getNombre(),
                        cita.getFechaCita(),
                        cita.getHoraCita(),
                        cita.getEstado().getNombre()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getById(@PathVariable("id") Integer id) {
        Optional<Cita> entidad = citaRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<CitaDTO> getCitaById(@PathVariable Integer id) {
        Optional<Cita> citaOptional = citaRepository.findById(id);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            CitaDTO citaDTO = new CitaDTO(
                    cita.getId(),
                    cita.getPaciente().getDocumento(),
                    cita.getPaciente().getTelefono(),
                    cita.getPaciente().getNombre(),
                    cita.getPaciente().getApellido(),
                    cita.getPaciente().getFechaNacimiento(),
                    cita.getMotivo(),
                    cita.getPaciente().getUsuario().getEmail());
            return ResponseEntity.ok(citaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody Cita entidad) {
        try {
            Cita _entidad = citaRepository.save(new Cita(null, 
            entidad.getPaciente(), 
            entidad.getMedico(), 
            entidad.getFechaCita(), 
            entidad.getHoraCita(), 
            entidad.getMotivo(), 
            entidad.getEstado(), 
            entidad.getFechaRegistrada(), 
            entidad.getHoraRegistrada()));
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(@PathVariable("id") Integer id, @RequestBody Cita entidad) {
        Cita _entidad = citaRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setPaciente(entidad.getPaciente());
            _entidad.setMedico(entidad.getMedico());
            _entidad.setFechaCita(entidad.getFechaCita());
            _entidad.setHoraCita(entidad.getHoraCita());
            _entidad.setMotivo(entidad.getMotivo());
            _entidad.setEstado(entidad.getEstado());
            _entidad.setFechaRegistrada(entidad.getFechaRegistrada());
            _entidad.setHoraRegistrada(entidad.getHoraRegistrada());
            return new ResponseEntity<>(citaRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            citaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}