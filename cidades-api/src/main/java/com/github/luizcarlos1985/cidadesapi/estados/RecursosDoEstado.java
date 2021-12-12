package com.github.luizcarlos1985.cidadesapi.estados;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class RecursosDoEstado {

    private final EstadoRepository repository;

    public RecursosDoEstado(final EstadoRepository repositorio) {
        this.repository = repositorio;
    }

    @GetMapping
    public List<Estado> estados() {
        return repository.findAll();
    }
}