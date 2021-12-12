package com.github.luizcarlos1985.cidadesapi.paises;

import com.github.luizcarlos1985.cidadesapi.paises.Pais;
import com.github.luizcarlos1985.cidadesapi.paises.repositorio.RepositorioDePais;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
public class RecursosDosPaises {

    private RepositorioDePais repository;

    public RecursosDosPaises(RepositorioDePais repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Pais> paises(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUm(@PathVariable Long id) {
        Optional<Pais> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
