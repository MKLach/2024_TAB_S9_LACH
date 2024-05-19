package com.mklachl.sopkom.model.dto.rozklad;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Przystanek;

public class RozkladDto {
	
	
	PrzystanekDto normalny;
	
	PrzystanekDto odwrotny;
	
	@JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Warsaw")
	Date date;
	
	HashMap<String, TreeSet<Time>> rozklad = new HashMap<String, TreeSet<Time>>();
	

	public RozkladDto(Przystanek p, KursDto kursDto, Date date) {
		normalny = new PrzystanekDto(p);
		if(p.getPrzystanekOdwrotny() != null) {
			odwrotny = new PrzystanekDto(p.getPrzystanekOdwrotny());
		}
		
		
		
	}
	
	public RozkladDto() {
		
		
	}

	public HashMap<String, TreeSet<Time>> getRozklad() {
		return rozklad;
	}

	public void setRozklad(HashMap<String, TreeSet<Time>> rozklad) {
		this.rozklad = rozklad;
	}
	
	
	public RozkladDto addKurs(LiniaDto linia, Time godzinna) {
		
		if(rozklad.containsKey(linia.getNumer())) {
			
			rozklad.get(linia.getNumer()).add(godzinna);
			
		} else {
			TreeSet<Time> list = new TreeSet<Time>();
			list.add(godzinna);
			rozklad.put(linia.getNumer(), list);
			
		}
	
		
		return this;
	}

	public PrzystanekDto getNormalny() {
		return normalny;
	}

	public void setNormalny(PrzystanekDto normalny) {
		this.normalny = normalny;
	}

	public PrzystanekDto getOdwrotny() {
		return odwrotny;
	}

	public void setOdwrotny(PrzystanekDto odwrotny) {
		this.odwrotny = odwrotny;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	
}
