package com.mklachl.sopkom.model.dto.kurs;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Kurs;

public abstract class AbstractKursDto {

	protected Long kursId;
	protected Long liniaId;
	
	protected Short harmonogramId;
	protected Short kierunek;
	protected Short typ_autobusu;
	
	
	public AbstractKursDto(Kurs kurs) {
		this.setKursId(kurs.getKursId());
		this.setKierunek(kurs.getKierunek());
		this.setTyp_autobusu(kurs.getTypAutobusu());	
	}
	
	public AbstractKursDto() {
		
	}
	
	
	
	public Long getKursId() {
		return kursId;
	}
	public void setKursId(Long kursId) {
		this.kursId = kursId;
	}
	public Long getLiniaId() {
		return liniaId;
	}
	public void setLiniaId(Long liniaId) {
		this.liniaId = liniaId;
	}
	public Short getHarmonogramId() {
		return harmonogramId;
	}
	public void setHarmonogramId(Short harmonogramId) {
		this.harmonogramId = harmonogramId;
	}
	public Short getKierunek() {
		return kierunek;
	}
	public void setKierunek(Short kierunek) {
		this.kierunek = kierunek;
	}
	public Short getTyp_autobusu() {
		return typ_autobusu;
	}
	public void setTyp_autobusu(Short typ_autobusu) {
		this.typ_autobusu = typ_autobusu;
	}


	
	
	
}
