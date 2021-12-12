package com.github.luizcarlos1985.cidadesapi.paises;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pais {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "nome_pt")
    private String englishName;

    @Column(name = "sigla")
    private String code;

    private Integer bacen;

    public Pais() {

    }

    public String getCode() {
        return code;
    }

    public Integer getBacen() {
        return bacen;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }
}
