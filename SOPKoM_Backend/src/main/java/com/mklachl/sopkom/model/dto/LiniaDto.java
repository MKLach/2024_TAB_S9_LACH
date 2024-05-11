package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Linia;
import java.util.List;

public class LiniaDto {
    private Long id;
    private String numer;

    private List<PrzystanekDto> przystanki;

    public LiniaDto() {

    }

    public LiniaDto(Linia Linia) {
        this.id = Linia.getLiniaId();
        this.numer = Linia.getNumer();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public List<PrzystanekDto> getPrzystanki() {
        return przystanki;
    }

    public void setPrzystanki(List<PrzystanekDto> przystanki) {
        this.przystanki = przystanki;
    }
}

