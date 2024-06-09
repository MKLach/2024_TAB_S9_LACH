package com.mklachl.sopkom.controller;

import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.PrzystanekDtoFull;
import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.repository.PrzystanekRepository;
import com.mklachl.sopkom.services.PrzystanekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/przystanek")
public class PrzystanekController {
    
    @Autowired
    PrzystanekService przystanekService;

    @Autowired
    PrzystanekRepository przystanekRepo;

    /**
     * Endpoint do pobrania przykładowego obiektu PrzystanekDto.
     * @return ResponseEntity z przykładowym PrzystanekDto
     */
    @GetMapping(path = "template", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> template() {
        PrzystanekDto dto = new PrzystanekDto();

        dto.setPrzystanekId(Long.valueOf(1));
        dto.setNazwa("Centralny");
        dto.setKodPocztowy("00-950");
        dto.setMiasto("Warszawa");
        dto.setUlica("Jana Pawła II");
        dto.setDlugoscGeograficzna("21.0122");
        dto.setSzerokoscGeograficzna("52.2297");
        
        dto.setPrzystanekOdwrotny(Long.valueOf(12));
        dto.setPrzystanekOdwrotnyNazwa("Przystanek odwrotny test (nie wymagane)");

        // Zwrócenie przykładowego DTO w odpowiedzi HTTP
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Endpoint do zapisu nowego przystanku.
     * @param przystanekDto DTO przystanku do zapisania
     * @return ResponseEntity z zapisanym PrzystanekDto
     */
    @PostMapping("/save")
    public ResponseEntity<?> addPrzystanek(@RequestBody PrzystanekDto przystanekDto){
        Przystanek przystanek = przystanekService.savePrzystanek(przystanekDto);
        return new ResponseEntity<>(new PrzystanekDto(przystanek), HttpStatus.OK);
    }

    /**
     * Endpoint do pobrania przystanku na podstawie ID.
     * @param id ID przystanku
     * @return ResponseEntity z PrzystanekDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPrzystanek(@PathVariable("id") Long id){
        Optional<Przystanek> przystanek = przystanekService.findPrzystanekById(id);
        if(przystanek.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new PrzystanekDto(przystanek.get()), HttpStatus.OK);
    }

    /**
     * Endpoint do usunięcia przystanku na podstawie ID.
     * @param id ID przystanku
     * @return ResponseEntity wskazujący wynik operacji
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrzystanek(@PathVariable("id") Long id) {
        if(przystanekRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        try {
            przystanekRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleMessage(e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Endpoint do aktualizacji przystanku na podstawie ID.
     * @param id ID przystanku
     * @param przystanekDto DTO przystanku z zaktualizowanymi polami
     * @return ResponseEntity z zaktualizowanym PrzystanekDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePrzystanek(@PathVariable("id") Long id, @RequestBody PrzystanekDto przystanekDto) {
        Optional<Przystanek> currentPrzystanek = przystanekRepo.findById(id);
        if(currentPrzystanek.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Przystanek przystanek = currentPrzystanek.get();

        przystanek.setNazwa(przystanekDto.getNazwa());
        przystanek.setKodPocztowy(przystanekDto.getKodPocztowy());
        przystanek.setMiasto(przystanekDto.getMiasto());
        przystanek.setUlica(przystanekDto.getUlica());
        przystanek.setDlugoscGeograficzna(przystanekDto.getDlugoscGeograficzna());
        przystanek.setSzerokoscGeograficzna(przystanekDto.getSzerokoscGeograficzna());
        
        if(przystanekDto.getPrzystanekOdwrotny() != null) {

            if(przystanekDto.getPrzystanekOdwrotny().longValue() == -1) {
                przystanek.setPrzystanekOdwrotny(null);
            } else {
                var przystanekOdwrotny = przystanekRepo.findById(przystanekDto.getPrzystanekOdwrotny());
                if(przystanekOdwrotny.isPresent()) {
                    przystanek.setPrzystanekOdwrotny(przystanekOdwrotny.get());
                } else {
                    System.out.println("no przystanek odwrotny present!");
                }
            }
        }
        
        przystanek = przystanekRepo.save(przystanek);
        return new ResponseEntity<>(new PrzystanekDto(przystanek), HttpStatus.OK);
    }

    /**
     * Endpoint do pobrania wszystkich przystanków.
     * @return ResponseEntity z listą PrzystanekDto
     */
    @GetMapping
    public ResponseEntity<?> getAllPrzystanki() {
        List<PrzystanekDto> list = new ArrayList<>();
        przystanekRepo.findAll().forEach(przystanek -> {
            list.add(new PrzystanekDto(przystanek));
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Endpoint do pobrania wszystkich przystanków z pełnymi informacjami.
     * @return ResponseEntity z listą PrzystanekDtoFull
     */
    @GetMapping("/full")
    public ResponseEntity<?> getAllPrzystankiFull() {
        List<PrzystanekDtoFull> list = new ArrayList<>();
        przystanekRepo.findAll().forEach(przystanek -> {
            list.add(new PrzystanekDtoFull(przystanek));
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Endpoint do pobrania przystanku z pełnymi informacjami na podstawie ID.
     * @param id ID przystanku
     * @return ResponseEntity z PrzystanekDtoFull
     */
    @GetMapping("/full/{id}")
    public ResponseEntity<?> getPrzystanekFull(@PathVariable("id") Long id){
        Optional<Przystanek> przystanek = przystanekService.findPrzystanekById(id);
        if(przystanek.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new PrzystanekDtoFull(przystanek.get()), HttpStatus.OK);
    }
}
