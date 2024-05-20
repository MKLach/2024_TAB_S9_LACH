package com.mklachl.sopkom.model.dto.kurs;

import java.util.ArrayList;
import java.util.List;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Kurs;

public class KursDto extends AbstractKursDto {

	public List<InputPrzystanekWKursieDto> przystanki;
	
	public HarmonogramDto harmonogram;
	
	public LiniaDto linia;
	
	boolean odwracalny;


	public KursDto() {
		
	}

	public KursDto(Kurs kurs) {
		super(kurs);
		this.setLinia(new LiniaDto(kurs.getLinia()));
		this.setHarmonogram(new HarmonogramDto(kurs.getHarmonogram()));
		this.setOdwracalny(kurs.getLinia().odwracalna());
		przystanki = new ArrayList<InputPrzystanekWKursieDto>();
		
		for(var k : kurs.getKursPrzystanekWlinii()) {
			
			przystanki.add(new InputPrzystanekWKursieDto(k));
		}
		
	}

	public List<InputPrzystanekWKursieDto> getPrzystanki() {
		return przystanki;
	}

	public void setPrzystanki(List<InputPrzystanekWKursieDto> przystanki) {
		this.przystanki = przystanki;
	}

	public void setHarmonogram(HarmonogramDto harmonogram) {
		this.setHarmonogramId(harmonogram.getHarmonogramId());
		this.harmonogram = harmonogram;
	}

	public LiniaDto getLinia() {
		return linia;
	}

	public void setLinia(LiniaDto linia) {
		this.linia = linia;
		this.setLiniaId(linia.getId());
	}

	public HarmonogramDto getHarmonogram() {
		return harmonogram;
		
	}

	public boolean getOdwracalny() {
		return odwracalny;
	}

	public void setOdwracalny(boolean b) {
		this.odwracalny = b;
	}


}
