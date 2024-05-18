package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("api/harmonogram")
public class HarmonogramController {

	
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
	
	
	
    @PostMapping
    public ResponseEntity<HarmonogramDto> createHarmonogram(@RequestBody HarmonogramDto harmonogram) {
        //HarmonogramDTO createdHarmonogram = harmonogramService.createHarmonogram(harmonogram);
       
        return ResponseEntity.ok(harmonogram);
    }

    @GetMapping
    public ResponseEntity<List<HarmonogramDto>> getAllHarmonograms() {
      
        return ResponseEntity.ok(harmonograms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarmonogramDto> getHarmonogramById(@PathVariable(name = "id") short id) {
        HarmonogramDto harmonogram = harmonograms.get(id-1);
       
        
        if (harmonogram != null) {
            return ResponseEntity.ok(harmonogram);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarmonogram(@PathVariable short id) {
        harmonograms.remove(id-1);
        
        
        return ResponseEntity.ok().build();
        
    }
	
	
}
