package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Linia;

import java.util.ArrayList;
import java.util.List;

public class LiniaDtoOutput extends LiniaDto {
    
    protected boolean odwracalna;

    private List<PrzystanekDtoDlaLinia> przystanki;

    public LiniaDtoOutput() {

    }

    public LiniaDtoOutput(Linia Linia) {
        this.id = Linia.getLiniaId();
        this.numer = Linia.getNumer();
        this.przystanki = new ArrayList<PrzystanekDtoDlaLinia>();
        
        odwracalna = true;
        
        for(int i = 0; i < Linia.getPrzystankiWlini().size(); i++) {
        	PrzystanekDtoDlaLinia dto = new PrzystanekDtoDlaLinia(Linia.getPrzystankiWlini().get(i));
        	this.przystanki.add(dto);
        	
        	if(dto.getPrzystanekOdwrotny() == null) {
        		odwracalna=false;
        	}
        }
        
    }

    public boolean isOdwracalna() {
		return odwracalna;
	}

	public void setOdwracalna(boolean odwracalna) {
		this.odwracalna = odwracalna;
	}

	public List<PrzystanekDtoDlaLinia> getPrzystanki() {
        return przystanki;
    }

    public void setPrzystanki(List<PrzystanekDtoDlaLinia> przystanki) {
        this.przystanki = przystanki;
    }
}

