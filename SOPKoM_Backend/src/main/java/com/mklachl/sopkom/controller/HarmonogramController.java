package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.repository.HarmonogramRepository;
import com.mklachl.sopkom.services.HarmonogramService;

@RestController
@RequestMapping("api/harmonogram")
public class HarmonogramController {

	
	// legacy
	/*
	public static List<HarmonogramDto> harmonograms = new ArrayList<HarmonogramDto>();
	
	static {

        {
        	  HarmonogramDto harmonogram = new HarmonogramDto();

              // Set sample data
              harmonogram.setHarmonogramId((short) 1);
              harmonogram.setNazwa("Dni robocze");
              harmonogram.setPon(true);
              harmonogram.setWto(true);
              harmonogram.setSro(true);
              harmonogram.setCzw(true);
              harmonogram.setPia(true);
              harmonogram.setSob(false);
              harmonogram.setNie(false);
              harmonogram.setDodatkoweInf("Dni robocze tylko.");

              harmonograms.add(harmonogram);
              
        }
        
        {
        	
        	  HarmonogramDto harmonogram = new HarmonogramDto();

              // Set sample data
              harmonogram.setHarmonogramId((short) 2);
              harmonogram.setNazwa("Dni robocze i weekendy");
              harmonogram.setPon(true);
              harmonogram.setWto(true);
              harmonogram.setSro(true);
              harmonogram.setCzw(true);
              harmonogram.setPia(true);
              harmonogram.setSob(true);
              harmonogram.setNie(true);
              harmonogram.setDodatkoweInf("Cały tydzien.");

              harmonograms.add(harmonogram);
        }
        
        {
        	
      	  HarmonogramDto harmonogram = new HarmonogramDto();

            // Set sample data
            harmonogram.setHarmonogramId((short) 3);
            harmonogram.setNazwa("weekendy");
            harmonogram.setPon(false);
            harmonogram.setWto(false);
            harmonogram.setSro(false);
            harmonogram.setCzw(false);
            harmonogram.setPia(false);
            harmonogram.setSob(true);
            harmonogram.setNie(true);
            harmonogram.setDodatkoweInf("Tylko weekendy.");

            harmonograms.add(harmonogram);
      }
        
		
	}
	*/
	@Autowired
	HarmonogramService service;
	
	@Autowired
	HarmonogramRepository repository;
	
    @PostMapping
    public ResponseEntity<HarmonogramDto> createHarmonogram(@RequestBody HarmonogramDto harmonogram) {
        //HarmonogramDTO createdHarmonogram = harmonogramService.createHarmonogram(harmonogram);
       
    	
    	
        return ResponseEntity.ok(harmonogram);
    }

    @GetMapping
    public ResponseEntity<List<HarmonogramDto>> getAllHarmonograms() {
      
    	var arr = new ArrayList<HarmonogramDto>();

    	List<HarmonogramDto> dtoList = repository.findAll().stream()
    			
                 .map(harmonogram -> new HarmonogramDto(harmonogram))
                 
                 
                 .collect(Collectors.toList());
    	
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarmonogramDto> getHarmonogramById(@PathVariable(name = "id") Short id) {
      
        var harmonogram = repository.findById(id).get();
        if (harmonogram != null) {
            return ResponseEntity.ok(new HarmonogramDto(harmonogram));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarmonogram(@PathVariable Short id) {
    	repository.deleteById(id);
        
        
        return ResponseEntity.ok().build();
        
    }
	
	
}
