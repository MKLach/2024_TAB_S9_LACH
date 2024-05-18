package com.mklachl.sopkom.model.dto.kurs;

import java.util.List;

public class InputKursDto extends AbstractKursDto {

	public List<InputPrzystanekWKursieDto> przystanki;
	
	public InputKursDto() {
		
	}
	
	public List<InputPrzystanekWKursieDto> getPrzystanki() {
		return przystanki;
	}

	public void setPrzystanki(List<InputPrzystanekWKursieDto> przystanki) {
		this.przystanki = przystanki;
	}
	
	
	
}
