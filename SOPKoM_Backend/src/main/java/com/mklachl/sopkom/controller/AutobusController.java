package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.services.AutobusService;

/***
 * Kontroler dla zasobu Autobus.
 * Obsługuje operacje tworzenia, pobierania, aktualizacji i usuwania autobusów.
 */
@RestController
@RequestMapping("api/autobus")
public class AutobusController {

    @Autowired
    AutobusService autobusService;

    @Autowired
    AutobusRepository autobusRepo;

    /**
     * Endpoint testowy.
     * @return Wiadomość testowa.
     */
    @GetMapping(path = {"test"}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test_autobus", HttpStatus.OK);
    }

    /**
     * Endpoint zwracający przykładowy obiekt AutobusDto.
     * @return Przykładowy obiekt AutobusDto.
     */
    @GetMapping(path = {"template"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> template() {
        AutobusDto dto = new AutobusDto();
        dto.setAutbousId(Long.valueOf(10));
        dto.setNumerRejestracyjny("SK55555");
        dto.setPrzebieg(41247.12f);
        dto.setPrzegladWaznyDo(new Date());
        dto.setStatus("OK");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Tworzy nowy autobus.
     * @param autobusDto Obiekt DTO autobusu.
     * @return Utworzony obiekt DTO autobusu.
     */
    @PostMapping("/save")
    public ResponseEntity<?> addAutobus(@RequestBody AutobusDto autobusDto) {
        var autobus = autobusService.saveAutobus(autobusDto);
        return new ResponseEntity<>(new AutobusDto(autobus), HttpStatus.OK);
    }

    /**
     * Pobiera autobus po identyfikatorze.
     * @param id Identyfikator autobusu.
     * @return Obiekt DTO autobusu lub wiadomość o błędzie.
     */
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAutobus(@PathVariable("id") Long id) {
        var autobus = autobusService.findAutobusById(id.longValue());
        if (autobus.isEmpty()) {
            return new ResponseEntity<>(new SimpleMessage("not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AutobusDto(autobus.get()), HttpStatus.OK);
    }

    /**
     * Usuwa autobus po identyfikatorze.
     * @param id Identyfikator autobusu.
     * @return Wiadomość o statusie operacji.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAutobus(@PathVariable("id") Long id) {
        if (autobusRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>(new SimpleMessage("no content"), HttpStatus.OK);
        }
        autobusRepo.deleteById(id);
        return new ResponseEntity<>(new SimpleMessage("deleted"), HttpStatus.OK);
    }

    /**
     * Aktualizuje autobus po identyfikatorze.
     * @param id Identyfikator autobusu.
     * @param autobusDto Obiekt DTO autobusu.
     * @return Wiadomość o statusie operacji.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAutobus(@PathVariable("id") Long id, @RequestBody AutobusDto autobusDto) {
        Optional<Autobus> autobus = autobusRepo.findById(id);
        if (autobus.isEmpty()) {
            return new ResponseEntity<>(new SimpleMessage("not found"), HttpStatus.NOT_FOUND);
        }
        Autobus in = autobus.get();
        in.setNumerRejestracyjny(autobusDto.getNumerRejestracyjny());
        in.setStatus(autobusDto.getStatus());
        in.setPrzebieg(autobusDto.getPrzebieg());
        in.setPrzegladWaznyDo(autobusDto.getPrzegladWaznyDo());
        in = autobusRepo.save(in);
        return new ResponseEntity<>(new SimpleMessage("patched"), HttpStatus.OK);
    }

    /**
     * Pobiera wszystkie autobusy.
     * @return Lista obiektów DTO autobusów.
     */
    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> getAllBuses() {
        List<AutobusDto> list = new ArrayList<>();
        autobusRepo.findAll().forEach(autobus -> list.add(new AutobusDto(autobus)));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Pobiera autobusy filtrowane po statusie.
     * @param status Status autobusu.
     * @return Lista obiektów DTO autobusów.
     */
    @GetMapping(path = {"/filtered"})
    public ResponseEntity<?> getAllBusesFilteredStatus(@RequestParam(name = "status", required = false) String status) {
        List<AutobusDto> list = new ArrayList<>();
        if (status == null || status.isBlank() || status.equals("ALL")) {
            autobusRepo.findAll().forEach(autobus -> list.add(new AutobusDto(autobus)));
        } else {
            autobusRepo.findByStatusLike("%" + status + "%").forEach(autobus -> list.add(new AutobusDto(autobus)));
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
