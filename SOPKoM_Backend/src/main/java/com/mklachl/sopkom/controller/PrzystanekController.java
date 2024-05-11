package com.mklachl.sopkom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.repository.PrzystanekRepository;
import com.mklachl.sopkom.services.PrzystanekService;

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

        // Zwrócenie przykładowego DTO w odpowiedzi HTTP
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> addPrzystanek(@RequestBody PrzystanekDto przystanekDto){
        Przystanek przystanek = przystanekService.savePrzystanek(przystanekDto);
        return new ResponseEntity<>(new PrzystanekDto(przystanek), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrzystanek(@PathVariable("id") Long id){
        Optional<Przystanek> przystanek = przystanekService.findPrzystanekById(id);
        if(przystanek.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new PrzystanekDto(przystanek.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrzystanek(@PathVariable("id") Long id) {
        if(przystanekRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        przystanekRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
        przystanek.setPrzystanekOdwrotny(przystanekDto.getPrzystanekOdwrotny());

        przystanek = przystanekRepo.save(przystanek);
        return new ResponseEntity<>(new PrzystanekDto(przystanek), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPrzystanki() {
        List<PrzystanekDto> list = new ArrayList<>();
        przystanekRepo.findAll().forEach(przystanek -> {
            list.add(new PrzystanekDto(przystanek));
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
