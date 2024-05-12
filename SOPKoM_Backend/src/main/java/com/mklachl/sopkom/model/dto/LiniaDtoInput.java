package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Linia;

import java.util.ArrayList;
import java.util.List;

public class LiniaDtoInput extends LiniaDto {
 
    private List<PrzystanekDtoDlaLiniaInput> przystanki;

    public LiniaDtoInput() {

    }

    public LiniaDtoInput(Linia Linia) {
        this.id = Linia.getLiniaId();
        this.numer = Linia.getNumer();
        przystanki = new ArrayList<PrzystanekDtoDlaLiniaInput>();
        
        for(int i = 0; i < Linia.getPrzystankiWlini().size(); i++) {
        	PrzystanekDtoDlaLiniaInput dto = new PrzystanekDtoDlaLiniaInput(Linia.getPrzystankiWlini().get(i));
        	this.przystanki.add(dto);
        	
        }
        
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

    public List<PrzystanekDtoDlaLiniaInput> getPrzystanki() {
        return przystanki;
    }

    public void setPrzystanki(List<PrzystanekDtoDlaLiniaInput> przystanki) {
        this.przystanki = przystanki;
    }
}

