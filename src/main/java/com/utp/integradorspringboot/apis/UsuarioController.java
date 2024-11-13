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

import com.utp.integradorspringboot.models.Usuario;
import com.utp.integradorspringboot.repositories.UsuarioRepository;
import com.utp.integradorspringboot.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(@RequestParam(required = false) String title) {
        try {
            List<Usuario> lista = usuarioRepository.findAll();
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id) {
        Optional<Usuario> entidad = usuarioRepository.findById(id);
        if (entidad.isPresent()) {
            return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario entidad) {
        try {
            // Llamar al servicio para registrar el usuario y asignar el rol "Paciente"
            Usuario nuevoUsuario = usuarioService.registrarUsuario(
                new Usuario(
                    null, 
                    entidad.getUsername(), 
                    entidad.getPassword(), 
                    entidad.getEmail(), 
                    entidad.getFechaCreacion(), 
                    entidad.getRoles())
            );
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Integer id, @RequestBody Usuario entidad) {
        Usuario _entidad = usuarioRepository.findById(id).orElse(null);
        if (_entidad != null) {
            _entidad.setUsername(entidad.getUsername());
            _entidad.setPassword(entidad.getPassword());
            _entidad.setEmail(entidad.getEmail());
            _entidad.setFechaCreacion(entidad.getFechaCreacion());
            _entidad.setRoles(entidad.getRoles());
            return new ResponseEntity<>(usuarioRepository.save(_entidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles/{documento}")
    public ResponseEntity<?> getRolesByDocumento(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.getRolesByUsername(username));
    }
}