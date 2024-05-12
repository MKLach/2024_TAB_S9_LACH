package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.PrzystanekWlini;

public class PrzystanekDtoDlaLinia extends PrzystanekDtoFull {
  
	private Short kolejnosc;

    public PrzystanekDtoDlaLinia(){

    }

    public PrzystanekDtoDlaLinia(PrzystanekWlini przystanekWlini){
    	super(przystanekWlini.getPrzystanek());
    	
        this.kolejnosc = przystanekWlini.getKolejnosc();
    }

   
	public Short getKolejnosc() {
		return kolejnosc;
	}

	public void setKolejnosc(Short kolejnosc) {
		this.kolejnosc = kolejnosc;
	}
    
}
