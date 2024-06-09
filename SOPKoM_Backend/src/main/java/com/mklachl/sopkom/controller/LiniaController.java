package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.exceptions.LiniaNotFoundException;
import com.mklachl.sopkom.exceptions.PrzystanekNotFoundException;
import com.mklachl.sopkom.model.dto.linia.LiniaDtoInput;
import com.mklachl.sopkom.model.dto.linia.LiniaDtoOutput;
import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLiniaInput;
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

    /**
     * Endpoint zwracający szablonowy obiekt LiniaDtoOutput.
     * @return Szablonowy obiekt LiniaDtoOutput
     */
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
        
        return new ResponseEntity<>(liniaDto, HttpStatus.OK);
    }
    
    /**
     * Endpoint zwracający szablonowy obiekt LiniaDtoInput.
     * @return Szablonowy obiekt LiniaDtoInput
     */
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

        return new ResponseEntity<>(liniaDto, HttpStatus.OK);
    }

    /**
     * Endpoint do dodawania nowej linii.
     * @param liniaDto DTO linii do dodania
     * @return Dodany obiekt LiniaDtoOutput
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

    /**
     * Endpoint do pobierania linii według ID.
     * @param id ID linii
     * @return Obiekt LiniaDtoOutput
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getLinia(@PathVariable("id") Long id){
        Optional<Linia> linia = liniaRepository.findById(id);
        
        if(linia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(new LiniaDtoOutput(linia.get()), HttpStatus.OK);
    }

    /**
     * Endpoint do usuwania linii według ID.
     * @param id ID linii
     * @return Odpowiedź HTTP
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLinia(@PathVariable("id") Long id) {
        if(liniaRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        liniaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint do aktualizacji linii.
     * @param id ID linii
     * @param liniaDtoInput DTO linii do aktualizacji
     * @return Zaktualizowany obiekt LiniaDtoOutput
     */
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

    /**
     * Endpoint do pobierania wszystkich linii.
     * @return Lista wszystkich linii
     */
    @GetMapping
    public ResponseEntity<?> getAllPrzystanki() {
        List<LiniaDtoOutput> list = new ArrayList<>();
        
        liniaRepository.findAll().forEach(linia -> {
            list.add(new LiniaDtoOutput(linia));
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Endpoint do pobierania wszystkich linii z pełnymi danymi.
     * @return Lista wszystkich linii z pełnymi danymi
     */
    @GetMapping("/input")
    public ResponseEntity<?> getAllPrzystankiFull() {
    	  List<LiniaDtoInput> list = new ArrayList<>();
          
          liniaRepository.findAll().forEach(linia -> {
              list.add(new LiniaDtoInput(linia));
          });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Endpoint do pobierania pełnych danych linii według ID.
     * @param id ID linii
     * @return Obiekt LiniaDtoInput
     */
    @GetMapping("/input/{id}")
    public ResponseEntity<?> getPrzystanekFull(@PathVariable("id") Long id){
    	  Optional<Linia> linia = liniaRepository.findById(id);
          
          if(linia.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          
          return new ResponseEntity<>(new LiniaDtoInput(linia.get()), HttpStatus.OK);
    }
    
}
