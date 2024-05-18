package com.mklachl.sopkom.model.dto.kurs;

import java.sql.Time;
import java.util.List;

import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

public class AbstractPrzystanekWKursieDto {

	protected Long przystanekwKursieId;
	
	protected Time godzinna;
	
	public AbstractPrzystanekWKursieDto() {
		
	}
	
	public AbstractPrzystanekWKursieDto(KursPrzystanekWlini kpw) {
		this.godzinna = new Time(kpw.getGodzinna().getTime());
		this.przystanekwKursieId = kpw.getKursPrzystanekWliniId();
	}

	public Long getPrzystanekwKursieId() {
		return przystanekwKursieId;
	}

	public void setPrzystanekwKursieId(Long przystanekwKursieId) {
		this.przystanekwKursieId = przystanekwKursieId;
	}

	public Time getGodzinna() {
		return godzinna;
	}

	public void setGodzinna(Time godzinna) {
		this.godzinna = godzinna;
	}
	
	
	
}
