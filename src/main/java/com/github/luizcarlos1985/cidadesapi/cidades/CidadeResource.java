package com.github.luizcarlos1985.cidadesapi.cidades;

import com.github.luizcarlos1985.cidadesapi.cidades.Cidade;
import com.github.luizcarlos1985.cidadesapi.cidades.CidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class CidadeResource {

    private final CidadeRepository repository;

    public CidadeResource(final CidadeRepository repository) {
        this.repository = repository;
    }


 /* @GetMapping
  public List<Cidade> cidades() {
      return repository.findAll();
  } */

    // 2nd - Pageable
    @GetMapping
    public Page<Cidade> cidades(final Pageable page) {
        return repository.findAll(page);
    }
}