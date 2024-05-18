package com.mklachl.sopkom.model.dto.kurs;

import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

public class InputPrzystanekWKursieDto extends AbstractPrzystanekWKursieDto {

	PrzystanekDtoDlaLinia przystanekWLini;
	
	public InputPrzystanekWKursieDto() {
		
	}
	
	public InputPrzystanekWKursieDto(KursPrzystanekWlini kpw) {
		super(kpw);
		przystanekWLini = new PrzystanekDtoDlaLinia(kpw.getPrzystanekWlini());
	}

	public PrzystanekDtoDlaLinia getPrzystanekWLini() {
		return przystanekWLini;
	}

	public void setPrzystanekWLini(PrzystanekDtoDlaLinia przystanekWLini) {
		this.przystanekWLini = przystanekWLini;
	}
	
	
	
	
}
