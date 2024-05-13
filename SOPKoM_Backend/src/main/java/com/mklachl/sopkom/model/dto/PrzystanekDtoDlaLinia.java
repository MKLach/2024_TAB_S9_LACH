package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.PrzystanekWlini;

public class PrzystanekDtoDlaLinia extends PrzystanekDtoFull {
  
	private Short kolejnosc;

	private String adres;
	
    public PrzystanekDtoDlaLinia(){

    }

    public PrzystanekDtoDlaLinia(PrzystanekWlini przystanekWlini){
    	super(przystanekWlini.getPrzystanek());
    	
        this.kolejnosc = przystanekWlini.getKolejnosc();
        this.adres = przystanekWlini.getPrzystanek().getMiasto() + ", " + przystanekWlini.getPrzystanek().getUlica();
    }

   
	public Short getKolejnosc() {
		return kolejnosc;
	}

	public void setKolejnosc(Short kolejnosc) {
		this.kolejnosc = kolejnosc;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
    
	
	
}
