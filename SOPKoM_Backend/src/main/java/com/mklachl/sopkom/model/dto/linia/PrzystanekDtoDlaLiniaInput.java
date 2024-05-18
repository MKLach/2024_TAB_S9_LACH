package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;

public class PrzystanekDtoDlaLiniaInput {
    private Long przystanekId;

    private Short kolejnosc;

    public PrzystanekDtoDlaLiniaInput(){

    }

    public PrzystanekDtoDlaLiniaInput(PrzystanekWlini przystanek){
        this.przystanekId = przystanek.getPrzystanek().getPrzystanekId();
        this.kolejnosc = przystanek.getKolejnosc();
    }

    // Getters
    public Long getPrzystanekId() {
        return przystanekId;
    }
    
    public Short getKolejnosc() {
		return kolejnosc;
	}


    // Setters
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

	public void setKolejnosc(Short kolejnosc) {
		this.kolejnosc = kolejnosc;
	}
    
}
