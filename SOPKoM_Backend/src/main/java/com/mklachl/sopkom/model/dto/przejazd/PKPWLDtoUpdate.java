package com.mklachl.sopkom.model.dto.przejazd;

import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PKPWLDtoUpdate {

	Long id;
	@JsonFormat(pattern = "HH:mm:ss, dd.MM.yyyy", timezone = "UTC")
	Date date;
	
	public PKPWLDtoUpdate() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
