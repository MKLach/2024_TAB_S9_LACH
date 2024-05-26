package com.mklachl.sopkom.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoInput;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoOutput;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.raporty.rozkład.DateHelper;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.repository.HarmonogramRepository;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.services.PrzejazdService;

@RestController
@RequestMapping("/api/przejazd")
public class PrzejazdController {

	@Autowired
	public HarmonogramRepository harmonogramRepository;
	
	@Autowired
	public KursRepository kursRepository;
	
	@Autowired
	public PrzejazdService przejazdService;
	
	@Autowired
	public KierowcaRepository kierowcaRepository;
	
	@Autowired
	public AutobusRepository autobusRepository;
	
	@Autowired
	public PrzejazdRepository przejazdRepository;
	
	@PostMapping({"/save", "/save/"})
	public ResponseEntity<?> savePrzejazd(@RequestBody PrzejazdDtoInput input){
		
		System.out.println(input);
		
		przejazdService.savePrzejazd(input);
		
	
		return ResponseEntity.ok().build();
	}
	
	@GetMapping({"/{przejazd_id}", "/{przejazd_id}/"})
	public ResponseEntity<?> getPrzejazd(@PathVariable(name="przejazd_id") Long przejazdid){
		
		try {
			var p = przejazdService.findPrzejazdById(przejazdid).orElseThrow();
			
			PrzejazdDtoOutput output = new PrzejazdDtoOutput(p);
			
			return ResponseEntity.ok(output);
			
		} catch (Exception e) {
			
			return ResponseEntity.notFound().build();
		}
	
	}
	
	@DeleteMapping({"/{przejazd_id}", "/{przejazd_id}/"})
	public ResponseEntity<?> deletePrzejazd(@PathVariable(name="przejazd_id") Long przejazdid){
		
		try {
			przejazdRepository.deleteById(przejazdid);
			
			return ResponseEntity.ok("deleted");
			
		} catch (Exception e) {
			
			return ResponseEntity.notFound().build();
		}
	
	}
	
	
	
	
	@GetMapping("/allDrivers")
	public ResponseEntity<?> gatAllAvaliableDriversForKurs(@RequestParam(name="kurs_id") Long kursId , @RequestParam(name="data") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
		
		System.out.println(date);
		
		Kurs kurs;
		try {
			kurs = kursRepository.findById(kursId).orElseThrow();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		Date startRaw;
		Date endRaw;
		
		if(kurs.getKierunek().shortValue() == 0) {
			
			startRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(0).getGodzinna(), 
					date);
			
			endRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(),
					date);
			
			
		} else {
			startRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(), 
					date);
			
			endRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(0).getGodzinna(),
					date);
			
		}
		
		//var przejazdy = przejazdService.findPrzejazdyBeetweenDate(startRaw, endRaw);
		//var kierowcy = przejazdy.stream().map((item) -> item.getKierowca()).collect(Collectors.toList());
		
		var temp = kierowcaRepository.findAllByAvailability(startRaw, endRaw).stream().map(KierowcaDto::new).collect(Collectors.toList());
		
		return ResponseEntity.ok(temp);
	}
	
	@GetMapping("/allBuses")
	public ResponseEntity<?> gatAllAvaliableBusesForKurs(@RequestParam(name="kurs_id") Long kursId , @RequestParam(name="data") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
		
		System.out.println(date);
		
		Kurs kurs;
		try {
			kurs = kursRepository.findById(kursId).orElseThrow();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		Date startRaw;
		Date endRaw;
		
		if(kurs.getKierunek().shortValue() == 0) {
			
			startRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(0).getGodzinna(), 
					date);
			
			endRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(),
					date);
			
			
		} else {
			startRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(), 
					date);
			
			endRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(0).getGodzinna(),
					date);
			
		}
		
		//var przejazdy = przejazdService.findPrzejazdyBeetweenDate(startRaw, endRaw);
		//var kierowcy = przejazdy.stream().map((item) -> item.getKierowca()).collect(Collectors.toList());
		
		var temp = autobusRepository.findAllByAvailable(startRaw, endRaw).stream().filter((item ) -> { return item.getStatus().equals("OK");}) .map(AutobusDto::new).collect(Collectors.toList());
		
		
		
		return ResponseEntity.ok(temp);
	}
		
	
	@GetMapping("/allDates")
	public ResponseEntity<?> gatAllAvaliableDatesForKurs(@RequestParam(name="kurs_id") Long kursId){
		Kurs kurs;
		try {
			kurs = kursRepository.findById(kursId).orElseThrow();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		
		Harmonogram harmongram = kurs.getHarmonogram();
		
		var result = DateHelper.getAllDatesForHarmonogram(harmongram);
		var copy = new LinkedList<Date>(result);
		
		//for( var datein : result) {
		
		var data = przejazdService.findPrzejazdByKurs(kurs);
		
		System.out.println(data.size());
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		
		List<String> dates = data.stream()
                 .map(item -> format.format(item.getDataStartu()))
                 .collect(Collectors.toList());
		
		List<String> list = copy.stream().map(item -> format.format(item)).collect(Collectors.toList());
		
		list.removeAll(dates);
		 
		//System.out.println(dates);
		//System.out.println(copy);
		
		//copy.removeAll(dates);
		 
		
		
		
		return ResponseEntity.ok(list);
	}
	
}
