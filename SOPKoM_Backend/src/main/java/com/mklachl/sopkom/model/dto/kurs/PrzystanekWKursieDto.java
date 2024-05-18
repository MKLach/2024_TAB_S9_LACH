package com.mklachl.sopkom.model.dto.kurs;

import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

public class PrzystanekWKursieDto extends AbstractPrzystanekWKursieDto {

	PrzystanekDtoDlaLinia przystanekWLini;
	
	public PrzystanekWKursieDto() {
		
	}
	
	public PrzystanekWKursieDto(KursPrzystanekWlini pkw) {
		super(pkw);
		przystanekWLini = new PrzystanekDtoDlaLinia(pkw.getPrzystanekWlini());
		
	}

	public PrzystanekDtoDlaLinia getPrzystanekWLini() {
		return przystanekWLini;
	}

	public void setPrzystanekWLini(PrzystanekDtoDlaLinia przystanekWLini) {
		
		this.przystanekWLini = przystanekWLini;
	}
	
	
	
}
