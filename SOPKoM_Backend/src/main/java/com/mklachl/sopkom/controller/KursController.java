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
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.repository.LiniaRepository;
import com.mklachl.sopkom.repository.PrzystanekWliniRepository;
import com.mklachl.sopkom.services.LiniaService;

@RestController
@RequestMapping(path = "/api/kurs")
public class KursController {

	public static List<KursDto> kursy = new ArrayList<KursDto>();
	
	long currIndex = Long.valueOf(1);
	long currIndexPrzystanki = Long.valueOf(1);
	
	static TreeSet<Date> polishHolidays = new TreeSet<>();
	
	static {
		 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			
			 // Fixed holidays
	        polishHolidays.add(sdf.parse("01.01.2024")); // New Year's Day
	        polishHolidays.add(sdf.parse("06.01.2024")); // Epiphany
	        polishHolidays.add(sdf.parse("01.05.2024")); // Labour Day
	        polishHolidays.add(sdf.parse("03.05.2024")); // Constitution Day
	        polishHolidays.add(sdf.parse("15.08.2024")); // Assumption of Mary
	        polishHolidays.add(sdf.parse("01.11.2024")); // All Saints' Day
	        polishHolidays.add(sdf.parse("11.11.2024")); // Independence Day
	        polishHolidays.add(sdf.parse("25.12.2024")); // Christmas Day
	        polishHolidays.add(sdf.parse("26.12.2024")); // Second Day of Christmas
	        
	        Date easterSunday = calculateEasterSunday(2024);
	        polishHolidays.add(easterSunday); // Easter Sunday
	        polishHolidays.add(addDays(easterSunday, 1)); // Easter Monday
	        polishHolidays.add(addDays(easterSunday, 49)); // Pentecost
	        polishHolidays.add(addDays(easterSunday, 60)); // Corpus Christi
	        
		} catch (Exception e) {
		
		}
		
		
	}
	
	 // Helper method to add days to a date
    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    // Method to calculate the date of Easter Sunday
    private static Date calculateEasterSunday(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day); // month is 0-based
        return cal.getTime();
    }
	
	
	static KursDto getById(long id) {
		
		for(int i = 0; i < kursy.size(); i++) {
			
			if(id == kursy.get(i).getKursId().longValue()) {
				return kursy.get(i);
			} 
		}
		
		return null;
		
	}
	static void deleteById(long id) {
		if(getById(id) != null) {
			kursy.remove(getById(id));
		}
	}
	
	static class $ implements Comparable<$>{
		Time t;
		LiniaDto linia;
		
		
		public $(Time t, LiniaDto linia) {
			super();
			this.t = t;
			this.linia = linia;
		}

		@Override
		public int compareTo($ o) {
			
			return t.compareTo(o.t);
		}

	}
	
	/**
	 * SUNDAY - first
	 * MONMDAY - second
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	static TreeSet<$> getAllByPrzystanekId(Long id, Date date) {
		
		TreeSet<$> data = new TreeSet<>();
		for(int i = 0; i < kursy.size(); i++) {
			KursDto kurs = kursy.get(i);
			HarmonogramDto harmonogram = kurs.getHarmonogram();
			
			if(harmonogramZalicza(date, harmonogram)) {
				
				for(int p_ind = 0; p_ind < kurs.getPrzystanki().size(); p_ind++) {
					var przystanek =  kurs.getPrzystanki().get(p_ind);
					if(przystanek.getPrzystanekWLini().getPrzystanekId().equals(id)) {
						data.add(new $(przystanek.getGodzinna(), kurs.getLinia()));
						continue;
					}
					
				}
				
			}
		}
		
		return data;
		
	}
	
	static boolean harmonogramZalicza(Date date, HarmonogramDto harm) {
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		
		//System.out.println(c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pl")));
		int day_val = c.get(Calendar.DAY_OF_WEEK);
		
		
		// TODO ;#
		if(polishHolidays.contains(date)) {
			return false;
		}
	
		switch (day_val) {
			case 1: {
				return  harm.isNie();
			}
			case 2: {
				return  harm.isPon();	
			}
			case 3: {
				return  harm.isWto();
			}
			case 4: {
				return  harm.isSro();
				
			}
			case 5: {
				return  harm.isCzw();
			}
			case 6: {
				return  harm.isPia();
				
			}
			case 7: {
				return  harm.isSob();
			}
			default:
				return false;
			}
		
	}
	
	@Autowired
	public LiniaService liniaService;
	
	@Autowired
	public LiniaRepository liniaRepository;
	
	@Autowired
	public PrzystanekWliniRepository przystnekWliniRepository;
	

	@GetMapping("/template/{id}")
    public ResponseEntity<?> getTemplateKurs(@PathVariable(name = "id") Long linia_id) {
		
		KursDto dto = new KursDto();
		
		Linia lIn = liniaRepository.findById(linia_id).orElseThrow();
		
		dto.setHarmonogram(HarmonogramController.harmonograms.get(0));
		
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
	
	
	@GetMapping(path ={"", "/"})
    public ResponseEntity<?> getAllKursy(@RequestParam(name="linia_id", defaultValue = "-1") Long linia_id) {
		
		// if -1 then ret all
		if(linia_id == -1) {
			 return ResponseEntity.ok(kursy);
		}
		
		Optional<Linia> op = liniaService.findLiniaById(linia_id);
		
		if(op.isEmpty()) {
			 return ResponseEntity.notFound().build();
		}
		
		
		var others = new ArrayList<>(kursy);
		
		others.removeIf((k) -> {
			
			return k.getLinia().getId() != linia_id;
		});
		
		
        return ResponseEntity.ok(others);
    }
	
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getKurs(@PathVariable(name="id") Long id) {
		
        return ResponseEntity.ok(getById(id));
    }
	
	@PostMapping("/save")
	public ResponseEntity<?> addKurs(@RequestBody InputKursDto inputKursDto) {
		ObjectMapper mapper = new ObjectMapper();
		
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
		
		var przystanki =new ArrayList<InputPrzystanekWKursieDto>();
		
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
		}
		
        return ResponseEntity.badRequest().build();
    }
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateKurs(@PathVariable(name="id") Long id, @RequestBody KursDto inputKursDto) {
		
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
		
        return ResponseEntity.badRequest().build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deletKurs(@PathVariable(name="id") Long id) {
		
		deleteById(id);
		
        return ResponseEntity.ok(getById(id));
    }
	
	
}
