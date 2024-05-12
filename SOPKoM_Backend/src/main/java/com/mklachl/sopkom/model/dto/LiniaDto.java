package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Linia;
import java.util.List;

public class LiniaDto {
    protected Long id;
    protected String numer;

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

    
}

