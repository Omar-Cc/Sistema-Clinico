package com.utp.integradorspringboot.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.integradorspringboot.models.Categoria;
import com.utp.integradorspringboot.repositories.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        return categoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetails) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaDetails.getNombre());
                    categoria.setDescripcion(categoriaDetails.getDescripcion());
                    categoria.setCategoriaPadre(categoriaDetails.getCategoriaPadre());
                    return ResponseEntity.ok(categoriaRepository.save(categoria));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.delete(categoria.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}