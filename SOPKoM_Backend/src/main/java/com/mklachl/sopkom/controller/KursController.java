package com.mklachl.sopkom.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.kurs.InputKursDto;
import com.mklachl.sopkom.model.dto.kurs.InputPrzystanekWKursieDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.repository.LiniaRepository;
import com.mklachl.sopkom.repository.PrzystanekWliniRepository;
import com.mklachl.sopkom.services.KursService;
import com.mklachl.sopkom.services.LiniaService;


/**
 * Kontroler dla zasobu Kurs.
 * Obsługuje operacje tworzenia, pobierania, aktualizacji i usuwania kursów.
 */
@RestController
@RequestMapping(path = "/api/kurs")
public class KursController {

	public static List<KursDto> kursy = new ArrayList<KursDto>();
	
	long currIndex = Long.valueOf(1);
	long currIndexPrzystanki = Long.valueOf(1);
	
	
	@Autowired
	public LiniaService liniaService;
	
	@Autowired
	public LiniaRepository liniaRepository;
	
	@Autowired
	public PrzystanekWliniRepository przystnekWliniRepository;
	

	@Autowired
	public KursService kursService;
	
	@Autowired
	public KursRepository kursRepo;
	
	/**
     * Szablon obiektu KursDto.
     * @param linia_id Identyfikator linii.
     * @return Obiekt KursDto.
     */
	@GetMapping("/template/{id}")
    public ResponseEntity<?> getTemplateKurs(@PathVariable(name = "id") Long linia_id) {
		
		KursDto dto = new KursDto();
		
		Linia lIn = liniaRepository.findById(linia_id).orElseThrow();
		
		dto.setHarmonogram(new HarmonogramDto());
		
		dto.setLinia(new LiniaDto(lIn));
		
		dto.setOdwracalny(lIn.odwracalna());
		
		dto.setKierunek((short)0);
		
		dto.setKursId(1l);
		
		List<InputPrzystanekWKursieDto> pk = new ArrayList<InputPrzystanekWKursieDto>();
		
		int h = 12;
		
		Time start = Time.valueOf(h + ":00:00");
		h++;
		
		for(int i = 0; i < lIn.getPrzystankiWlini().size(); i++) {
			
			InputPrzystanekWKursieDto pkwDto = new InputPrzystanekWKursieDto();
			
			pkwDto.setGodzinna(start);
			start = Time.valueOf(h + ":00:00");
			h++;
			
			pkwDto.setPrzystanekwKursieId((Long) (long)(i+1));
			pkwDto.setPrzystanekWLini(new PrzystanekDtoDlaLinia(lIn.getPrzystankiWlini().get(i)));
			
			pk.add(pkwDto);
		}
		
		dto.setPrzystanki(pk);
		dto.setTyp_autobusu((short)0);
		
		
        return ResponseEntity.ok(dto);
    }
	
	/**
     * Szablon obiektu InputKursDto.
     * @param linia_id Identyfikator linii.
     * @return Obiekt InputKursDto.
     */
	@GetMapping("/template/input/{id}")
    public ResponseEntity<?> getTemplateKursInput(@PathVariable(name = "id") Long linia_id) {
		
		InputKursDto dto = new InputKursDto();
		
		Linia lIn = liniaRepository.findById(linia_id).orElseThrow();
		
		dto.setHarmonogramId((short) 1);
		
		dto.setLiniaId(linia_id);
		
		dto.setKierunek((short)0);
		
		dto.setKursId(1l);
		
		List<InputPrzystanekWKursieDto> pk = new ArrayList<InputPrzystanekWKursieDto>();
		
		int h = 12;
		
		Time start = Time.valueOf(h + ":00:00");
		h++;
		
		for(int i = 0; i < lIn.getPrzystankiWlini().size(); i++) {
			
			InputPrzystanekWKursieDto pkwDto = new InputPrzystanekWKursieDto();
			
			pkwDto.setGodzinna(start);
			start = Time.valueOf(h + ":00:00");
			h++;
			
			pkwDto.setPrzystanekwKursieId((Long) (long)(i+1));
			pkwDto.setPrzystanekWLini(new PrzystanekDtoDlaLinia(lIn.getPrzystankiWlini().get(i)));
			
			pk.add(pkwDto);
		}
		
		dto.setPrzystanki(pk);
		dto.setTyp_autobusu((short)0);
		
		
        return ResponseEntity.ok(dto);
    }
	
	 /**
     * Pobiera wszystkie kursy lub filtrowane po identyfikatorze linii.
     * @param linia_id Identyfikator linii (opcjonalny).
     * @return Lista obiektów KursDto.
     */
	@GetMapping(path ={"", "/"})
    public ResponseEntity<?> getAllKursy(@RequestParam(name="linia_id", defaultValue = "-1") Long linia_id) {
		
		// if -1 then ret all
		System.out.println(linia_id);
		List<Kurs> kursy = null;
		var ret = new ArrayList<KursDto>();
		if(linia_id.equals(-1l)) {
			
			kursy = kursRepo.findAll();
			
		} else {
			Optional<Linia> op = liniaService.findLiniaById(linia_id);
		
			if(op.isEmpty()) {
				 return ResponseEntity.notFound().build();
			}
			
			kursy = kursService.findKursByLinia(op.get());
		}
		
		
		for(var kurs : kursy) {
		
			ret.add(new KursDto(kurs));
		}
		
	
		
        return ResponseEntity.ok(ret);
    }
	
	/**
     * Pobiera kurs po identyfikatorze.
     * @param id Identyfikator kursu.
     * @return Obiekt KursDto.
     */
	@GetMapping("/{id}")
    public ResponseEntity<?> getKurs(@PathVariable(name="id") Long id) {
		
        return ResponseEntity.ok(new KursDto(kursRepo.findById(id).get()));
    }
	/**
     * Tworzy nowy kurs.
     * @param inputKursDto Obiekt InputKursDto.
     * @return Status operacji.
     */
	@PostMapping("/save")
	public ResponseEntity<?> addKurs(@RequestBody InputKursDto inputKursDto) {
		
		// ah yes, legacy
		/*ObjectMapper mapper = new ObjectMapper();
		
		Optional<Linia> liniaIn = liniaService.findLiniaById(inputKursDto.getLiniaId());
		
		if(liniaIn.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		HarmonogramDto dto = HarmonogramController.harmonograms.get(inputKursDto.getHarmonogramId()-1);
	
		

		KursDto finale = new KursDto();
		finale.setHarmonogram(dto);
		finale.setLinia(new LiniaDto(liniaIn.get()));
		finale.setOdwracalny(liniaIn.get().odwracalna());
		finale.setKierunek(finale.getOdwracalny() ? inputKursDto.getKierunek() : 0);
		finale.setKursId(Long.valueOf(currIndex));
		currIndex+= 1;
		finale.setTyp_autobusu(inputKursDto.getTyp_autobusu());
		
		var przystanki = new ArrayList<InputPrzystanekWKursieDto>();
		
		for(int i = 0; i < inputKursDto.getPrzystanki().size(); i++) {
			var in = inputKursDto.getPrzystanki().get(i);
			InputPrzystanekWKursieDto out = new InputPrzystanekWKursieDto();
			
			out.setGodzinna(in.getGodzinna());
			out.setPrzystanekwKursieId(Long.valueOf(currIndexPrzystanki));
			currIndexPrzystanki++;
			
			var pwl =  przystnekWliniRepository.findById(in.getPrzystanekWLini().getPrzystanekwliniId());
			
			if(pwl.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			out.setPrzystanekWLini(new PrzystanekDtoDlaLinia(pwl.get()));
			
			przystanki.add(out);
			
		}
		finale.setPrzystanki(przystanki);
		
		if(kursy.add(finale)) {
			 return ResponseEntity.ok().body(inputKursDto);
		}*/
		
		try {
			kursService.saveKurs(inputKursDto);
			
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
        return ResponseEntity.badRequest().build();
    }
	/**
     * Aktualizuje kurs po identyfikatorze.
     * @param id Identyfikator kursu.
     * @param inputKursDto Obiekt InputKursDto.
     * @return Status operacji.
     */
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateKurs(@PathVariable(name="id") Long id, @RequestBody InputKursDto inputKursDto) {
		
		/*
		Optional<Linia> liniaIn = liniaService.findLiniaById(inputKursDto.getLiniaId());
		
		if(liniaIn.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		HarmonogramDto dto = HarmonogramController.harmonograms.get(inputKursDto.getHarmonogramId()-1);
	
		
		KursDto finale = getById(id);
		
		if(finale == null) {
			return ResponseEntity.noContent().build();
		}
		
		finale.setHarmonogram(dto);
		finale.setLinia(new LiniaDto(liniaIn.get()));
		finale.setOdwracalny(liniaIn.get().odwracalna());
		finale.setKierunek(finale.getOdwracalny() ? inputKursDto.getKierunek() : 0);
		finale.setTyp_autobusu(inputKursDto.getTyp_autobusu());
		
		var przystanki = new ArrayList<InputPrzystanekWKursieDto>();
		
		finale.setPrzystanki(przystanki);
		
		for(int i = 0; i < inputKursDto.getPrzystanki().size(); i++) {
			var in = inputKursDto.getPrzystanki().get(i);
			InputPrzystanekWKursieDto out = new InputPrzystanekWKursieDto();
			
			out.setGodzinna(in.getGodzinna());
			out.setPrzystanekwKursieId(currIndexPrzystanki++);
			
			var pwl = przystnekWliniRepository.findById(in.getPrzystanekWLini().getPrzystanekwliniId());
			
			if(pwl.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			out.setPrzystanekWLini(new PrzystanekDtoDlaLinia(pwl.get()));
			
			przystanki.add(out);
			
		}
		finale.setPrzystanki(przystanki);

		deleteById(finale.getKursId());
		
		if(kursy.add(finale)) {
			 return ResponseEntity.ok().body(inputKursDto);
		}
		8*/
		
		try {
			kursService.updateKurs(inputKursDto);
		} catch (Exception e) {
			ResponseEntity.notFound();
			e.printStackTrace();
		}
		
		
		 return ResponseEntity.ok().body(inputKursDto);
    }
	/**
     * Usuwa kurs po identyfikatorze.
     * @param id Identyfikator kursu.
     * @return Status operacji.
     */
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKurs(@PathVariable(name="id") Long id) {
		
		kursRepo.deleteById(id);
		
        return ResponseEntity.ok().build();
    }
	
	
}
