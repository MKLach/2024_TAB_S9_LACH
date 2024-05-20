package com.mklachl.sopkom.model.dto.rozklad;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Przystanek;

public class RozkladDto {
	
	public static class $ implements Comparable<$> {
		public Time time;
		public boolean odw;
		
		
		
		public $(Time time, boolean odw) {
			super();
			this.time = time;
			this.odw = odw;
		}
		@Override
		public int hashCode() {
			return Objects.hash(odw, time);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			$ other = ($) obj;
			return odw == other.odw && Objects.equals(time, other.time);
		}
		
		public int compareTo($ o) {
			
			return this.time.compareTo(o.time);
		};
		
		
	}
	
	
	PrzystanekDto normalny;
	
	@JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Warsaw")
	Date date;
	
	HashMap<String, TreeSet<$>> rozklad = new HashMap<String, TreeSet<$>>();
	

	public RozkladDto(Przystanek p, KursDto kursDto, Date date) {
		normalny = new PrzystanekDto(p);
	
		
	}
	
	public RozkladDto() {
		
		
	}

	public HashMap<String, TreeSet<$>> getRozklad() {
		return rozklad;
	}

	public void setRozklad(HashMap<String, TreeSet<$>> rozklad) {
		this.rozklad = rozklad;
	}
	
	
	public RozkladDto addKurs(LiniaDto linia, Time godzinna, boolean odwrotna) {
		
		if(rozklad.containsKey(linia.getNumer())) {
			
			rozklad.get(linia.getNumer()).add(new $(godzinna, odwrotna));
			
		} else {
			TreeSet<$> list = new TreeSet<$>();
			list.add(new $(godzinna, odwrotna));
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


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	
}
