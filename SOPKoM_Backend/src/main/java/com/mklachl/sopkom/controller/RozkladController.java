package com.mklachl.sopkom.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.rozklad.RozkladDto;
import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.raporty.rozkład.DateHelper;
import com.mklachl.sopkom.repository.PrzystanekRepository;

@RestController
@RequestMapping("/api/rozklad")
public class RozkladController {

	@Autowired
	public PrzystanekRepository repo;
	
	@Autowired
	public DateHelper rozkladHelper;
	
	@GetMapping()
	public ResponseEntity<?> test(@RequestParam(name="id") Long id, @RequestParam(name="date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
		
		Przystanek normalny = repo.findById(id).get();
		
		//Przystanek odwrotny = normalny.getPrzystanekOdwrotny();
		
		RozkladDto dto = new RozkladDto();
		dto.setNormalny(new PrzystanekDto(normalny));
		
		dto.setDate(date);
		
		//for(int i = 0; i < kur)
		
	
		var vat = rozkladHelper.getAllByPrzystanekId(id, date);
		
		for(var $$$ : vat) {
			dto.addKurs($$$.getLinia(), $$$.getT(), $$$.isOdw());
		}
		
		return new ResponseEntity<>(dto,
				HttpStatus.OK);
		
	}
	
	
	
}
