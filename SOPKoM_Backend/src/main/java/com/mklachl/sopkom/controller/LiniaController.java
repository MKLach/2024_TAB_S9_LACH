package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.exceptions.LiniaNotFoundException;
import com.mklachl.sopkom.exceptions.PrzystanekNotFoundException;
import com.mklachl.sopkom.model.dto.LiniaDtoInput;
import com.mklachl.sopkom.model.dto.LiniaDtoOutput;
import com.mklachl.sopkom.model.dto.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.dto.PrzystanekDtoDlaLiniaInput;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.repository.LiniaRepository;
import com.mklachl.sopkom.services.LiniaService;

@RestController
@RequestMapping("/api/linia")
public class LiniaController {
    @Autowired
    LiniaService liniaService;

    @Autowired
    LiniaRepository liniaRepository;

    @GetMapping(path = "template", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> template() {
        LiniaDtoOutput liniaDto = new LiniaDtoOutput();

        liniaDto.setNumer("M500");
        liniaDto.setId(Long.valueOf(1));
        
        List<PrzystanekDtoDlaLinia> list = new ArrayList<PrzystanekDtoDlaLinia>();
        
        for(Short i = 0; i < 5; i++) {
        	
        	PrzystanekDtoDlaLinia przystanek = new PrzystanekDtoDlaLinia();
        	przystanek.setKodPocztowy("44-100");
        	przystanek.setDlugoscGeograficzna((22.123 + i) + "");
        	przystanek.setSzerokoscGeograficzna((50.000- i) + "");
        	przystanek.setKolejnosc(i);
        	przystanek.setMiasto("Gliwce");
        	przystanek.setUlica("Ulica testowa " + i);
        	przystanek.setPrzystanekId(Long.valueOf(i));
        	
        	list.add(przystanek);
        	
        }
        
        liniaDto.setPrzystanki(list);
        
        //dto.setPrzystanekOdwrotny(Long.valueOf(12));
        //dto.setPrzystanekOdwrotnyNazwa("Przsytanek odwrotny test (nie wymagane)");

        // Zwrócenie przykładowego DTO w odpowiedzi HTTP
        return new ResponseEntity<>(liniaDto, HttpStatus.OK);
    }
    
    
    @GetMapping(path = "templateInput", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> templateInput() {
        LiniaDtoInput liniaDto = new LiniaDtoInput();

        liniaDto.setNumer("M500");
        liniaDto.setId(Long.valueOf(1));
        
        List<PrzystanekDtoDlaLiniaInput> list = new ArrayList<PrzystanekDtoDlaLiniaInput>();
        
        for(Short i = 0; i < 5; i++) {
        	
        	PrzystanekDtoDlaLiniaInput przystanek = new PrzystanekDtoDlaLiniaInput();
        
        	przystanek.setKolejnosc(i);
        	przystanek.setPrzystanekId(Long.valueOf(i));
        	
        	list.add(przystanek);
        	
        }
        
        liniaDto.setPrzystanki(list);
        
        //dto.setPrzystanekOdwrotny(Long.valueOf(12));
        //dto.setPrzystanekOdwrotnyNazwa("Przsytanek odwrotny test (nie wymagane)");

        // Zwrócenie przykładowego DTO w odpowiedzi HTTP
        return new ResponseEntity<>(liniaDto, HttpStatus.OK);
    }

    
    /**
     * 
     * przykładowe co ma być wysłane do bazy, 
       wybór przystanklów to ma być dropdown
       przysantki będziemy tworzyli w osebnej kategori
       a pózniej je wybierali z tworzenia lini
       
       KOLEJNOSC STARTUJEMY OD 1, NIE OD 0!!!
       
       https://ej2.syncfusion.com/react/documentation/drop-down-list/filtering
       (jakiś przykład)
       
    {
	    "id": 1,
	    "numer": "M500",
	    "przystanki": [
	        {
	            "przystanekId": 1,
	            "kolejnosc": 1
	        },
	        {
	            "przystanekId": 2,
	            "kolejnosc": 2
	        },
	        {
	            "przystanekId": 3,
	            "kolejnosc": 3
	        },
	        {
	            "przystanekId": 4,
	            "kolejnosc": 4,
	        }
	    ]
	}
     
     */
    @PostMapping("/save")
    public ResponseEntity<?> addLinia(@RequestBody LiniaDtoInput liniaDto){
       
    	Linia linia;
		try {
			linia = liniaService.saveLinia(liniaDto);
			
		} catch (PrzystanekNotFoundException e) {
			 return new ResponseEntity<>(e , HttpStatus.NOT_FOUND);
			
		}
        return new ResponseEntity<>(new LiniaDtoOutput(linia), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLinia(@PathVariable("id") Long id){
        Optional<Linia> linia = liniaRepository.findById(id);
        
        if(linia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
       
        
        
        return new ResponseEntity<>(new LiniaDtoOutput(linia.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLinia(@PathVariable("id") Long id) {
        if(liniaRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        liniaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLinia(@PathVariable("id") Long id, @RequestBody LiniaDtoInput liniaDtoInput) {
        
    	Linia updated;
    	liniaDtoInput.setId(id);
		try {
			updated = liniaService.patchLinia(liniaDtoInput);
		} catch (PrzystanekNotFoundException | LiniaNotFoundException e) {
			
			return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<>(new LiniaDtoOutput(updated), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPrzystanki() {
        List<LiniaDtoOutput> list = new ArrayList<>();
        
        
        
        liniaRepository.findAll().forEach(linia -> {
            list.add(new LiniaDtoOutput(linia));
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/input")
    public ResponseEntity<?> getAllPrzystankiFull() {
    	  List<LiniaDtoInput> list = new ArrayList<>();
          
          liniaRepository.findAll().forEach(linia -> {
              list.add(new LiniaDtoInput(linia));
          });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/input/{id}")
    public ResponseEntity<?> getPrzystanekFull(@PathVariable("id") Long id){
    	  Optional<Linia> linia = liniaRepository.findById(id);
          
          if(linia.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          
         
          return new ResponseEntity<>(new LiniaDtoInput(linia.get()), HttpStatus.OK);
    }
    
}
