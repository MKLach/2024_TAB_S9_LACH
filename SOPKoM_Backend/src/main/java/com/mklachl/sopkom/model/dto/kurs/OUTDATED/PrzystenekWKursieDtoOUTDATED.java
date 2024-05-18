package com.mklachl.sopkom.model.dto.kurs.OUTDATED;

import java.sql.Time;

import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.PrzystanekDtoFull;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

public class PrzystenekWKursieDtoOUTDATED {

	protected Long przystanekkurs_id;
	
	protected PrzystanekDtoFull przystanek;
	
	protected short kolejnosc;
	
	protected Time godzinna;
	
	public PrzystenekWKursieDtoOUTDATED() {
		
	}
	
	public PrzystenekWKursieDtoOUTDATED(KursPrzystanekWlini kwl) {
		godzinna = new Time(kwl.getGodzinna().getTime());
		przystanek = new PrzystanekDtoFull(kwl.getPrzystanekWlini().getPrzystanek());
		kolejnosc = kwl.getPrzystanekWlini().getKolejnosc();
		przystanekkurs_id = Long.valueOf(9);
		
	}

	public Long getPrzystanekkurs_id() {
		return przystanekkurs_id;
	}

	public void setPrzystanekkurs_id(Long przsytanekkurs_id) {
		this.przystanekkurs_id = przsytanekkurs_id;
	}

	public PrzystanekDtoFull getPrzystanek() {
		return przystanek;
	}

	public void setPrzystanek(PrzystanekDtoFull przsytanek) {
		this.przystanek = przsytanek;
	}

	public Time getGodzinna() {
		return godzinna;
	}

	public void setGodzinna(Time t) {
		this.godzinna = t;
	}
	
	
	
}
