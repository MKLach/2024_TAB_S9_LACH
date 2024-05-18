package com.mklachl.sopkom.model.dto.kurs.OUTDATED;

import java.util.List;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Linia;

public class KursDtoOUTDATED {
	
	protected LiniaDto linia;
	protected Long kursId;
	
	protected boolean odwracalny;
	// ZERO - normal // ONE - odwrony
	protected Short kieruenk;
	
	protected HarmonogramDto harmonogram;
	
	protected List<PrzystenekWKursieDtoOUTDATED> przystanki;
	
	public KursDtoOUTDATED() {
		
	}
	
	public KursDtoOUTDATED(Kurs kurs) {
		Linia linia  = kurs.getLinia();
		odwracalny = linia.odwracalna();
		kieruenk = kurs.getKierunek();
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public Long getKursId() {
		return kursId;
	}

	public void setKursId(Long kursId) {
		this.kursId = kursId;
	}

	public HarmonogramDto getHarmonogram() {
		return harmonogram;
	}

	public void setHarmonogram(HarmonogramDto harmonogram) {
		this.harmonogram = harmonogram;
	}

	public LiniaDto getLinia() {
		return linia;
	}

	public void setLinia(LiniaDto linia) {
		this.linia = linia;
	}

	public boolean isOdwracalny() {
		return odwracalny;
	}

	public void setOdwracalny(boolean odwracalny) {
		this.odwracalny = odwracalny;
	}

	public List<PrzystenekWKursieDtoOUTDATED> getPrzystanki() {
		return przystanki;
	}

	public void setPrzystanki(List<PrzystenekWKursieDtoOUTDATED> przystanki) {
		this.przystanki = przystanki;
	}

	public Short getKieruenk() {
		return kieruenk;
	}

	public void setKieruenk(Short kieruenk) {
		this.kieruenk = kieruenk;
	}
	
	
	
	
}
