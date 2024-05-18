package com.mklachl.sopkom.model.dto.kurs;



public abstract class AbstractKursDto {

	protected Long kursId;
	protected Long liniaId;
	
	protected Short harmonogramId;
	protected Short kierunek;
	protected Short typ_autobusu;
	
	
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
