package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.repository.HarmonogramRepository;
import com.mklachl.sopkom.services.HarmonogramService;

/**
 * Kontroler dla zasobu Harmonogram.
 * Obsługuje operacje tworzenia, pobierania i usuwania harmonogramów.
 */
@RestController
@RequestMapping("api/harmonogram")
public class HarmonogramController {

    @Autowired
    HarmonogramService service;

    @Autowired
    HarmonogramRepository repository;

    /**
     * Tworzy nowy harmonogram.
     * @param harmonogram Obiekt DTO harmonogramu.
     * @return Utworzony obiekt DTO harmonogramu.
     */
    @PostMapping
    public ResponseEntity<HarmonogramDto> createHarmonogram(@RequestBody HarmonogramDto harmonogram) {
        return ResponseEntity.ok(harmonogram);
    }

    /**
     * Pobiera wszystkie harmonogramy.
     * @return Lista obiektów DTO harmonogramów.
     */
    @GetMapping
    public ResponseEntity<List<HarmonogramDto>> getAllHarmonograms() {
        List<HarmonogramDto> dtoList = repository.findAll().stream()
                .map(HarmonogramDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    /**
     * Pobiera harmonogram po identyfikatorze.
     * @param id Identyfikator harmonogramu.
     * @return Obiekt DTO harmonogramu lub wiadomość o błędzie.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HarmonogramDto> getHarmonogramById(@PathVariable(name = "id") Short id) {
        var harmonogram = repository.findById(id).get();
        if (harmonogram != null) {
            return ResponseEntity.ok(new HarmonogramDto(harmonogram));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Usuwa harmonogram po identyfikatorze.
     * @param id Identyfikator harmonogramu.
     * @return Status operacji.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarmonogram(@PathVariable Short id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
