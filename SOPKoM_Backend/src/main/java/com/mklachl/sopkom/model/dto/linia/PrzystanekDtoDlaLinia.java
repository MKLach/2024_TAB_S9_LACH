package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.dto.PrzystanekDtoFull;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;

public class PrzystanekDtoDlaLinia extends PrzystanekDtoFull {
  
	private Short kolejnosc;

	private String adres;
	
	private Long przystanekwliniId;
	
    public PrzystanekDtoDlaLinia(){

    }

    public PrzystanekDtoDlaLinia(PrzystanekWlini przystanekWlini){
    	super(przystanekWlini.getPrzystanek());
    	this.przystanekwliniId = przystanekWlini.getPrzystanekWliniId();
    	
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

	public Long getPrzystanekwliniId() {
		return przystanekwliniId;
	}

	public void setPrzystanekwliniId(Long przystanekwliniId) {
		this.przystanekwliniId = przystanekwliniId;
	}
    
	
	
}
