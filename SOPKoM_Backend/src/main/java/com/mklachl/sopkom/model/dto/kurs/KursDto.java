package com.mklachl.sopkom.model.dto.kurs;

import java.util.List;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Kurs;

public class KursDto extends AbstractKursDto {

	public List<InputPrzystanekWKursieDto> przystanki;
	
	public HarmonogramDto harmonogram;
	
	public LiniaDto linia;
	
	boolean odwracalny;

	private Short typAutobusu;
	
	public KursDto() {
		
	}

	public KursDto(Kurs kurs) {
		this.setKursId(kurs.getKursId());
		this.setLinia(new LiniaDto(kurs.getLinia()));
		this.setHarmonogram(new HarmonogramDto(kurs.getHarmonogram()));
		this.setKierunek(kurs.getKierunek());
		this.setTypAutobusu(kurs.getTypAutobusu());
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

	public Short getTypAutobusu() {
		return typAutobusu;
	}

	public void setTypAutobusu(Short typAutobusu) {
		this.typAutobusu = typAutobusu;
	}

	
	
}
