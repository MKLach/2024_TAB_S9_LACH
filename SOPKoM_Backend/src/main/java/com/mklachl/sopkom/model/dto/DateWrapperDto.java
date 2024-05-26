package com.mklachl.sopkom.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateWrapperDto {

	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date date;
	
	
	public DateWrapperDto(Date date) {
		this.date = date;
		
	}
	
	public DateWrapperDto() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
