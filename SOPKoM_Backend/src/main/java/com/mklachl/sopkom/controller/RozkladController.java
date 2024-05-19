package com.mklachl.sopkom.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.dto.rozklad.RozkladDto;
import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.repository.PrzystanekRepository;

@RestController
@RequestMapping("/api/rozklad")
public class RozkladController {

	@Autowired
	public PrzystanekRepository repo;
	
	@GetMapping("test")
	public ResponseEntity<?> test(@RequestParam(name="id") Long id, @RequestParam(name="date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
		
		var data = KursController.kursy;
	
		Przystanek normalny = repo.findById(id).get();
		
		Przystanek odwrotny = normalny.getPrzystanekOdwrotny();
		
		RozkladDto dto = new RozkladDto();
		dto.setNormalny(new PrzystanekDto(normalny));
		dto.setOdwrotny(new PrzystanekDto(odwrotny));
		dto.setDate(date);
		
		//for(int i = 0; i < kur)
		
	
		var vat = KursController.getAllByPrzystanekId(id, date);
		
		for(var $$$ : vat) {
			dto.addKurs($$$.linia, $$$.t);
		}
		System.out.println("aaa "+date);
		return new ResponseEntity<>(dto,
				HttpStatus.OK);
		
	}
	
	
	
}
