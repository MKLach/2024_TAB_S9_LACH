package com.mklachl.sopkom.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.exceptions.IncydentException;
import com.mklachl.sopkom.model.dto.IncydentDto;
import com.mklachl.sopkom.model.dto.IncydentDtoOutput;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.repository.IncydentRepository;
import com.mklachl.sopkom.services.IncydentService;

/**
 * Kontroler dla zasobu Incydent.
 * Obsługuje operacje tworzenia, pobierania, aktualizacji i usuwania incydentów.
 */
@RestController
@RequestMapping("/api/incydent")
public class IncydentController {

    @Autowired
    public IncydentService incydentService;

    @Autowired
    public IncydentRepository incydentRepository;

    /**
     * Tworzy nowy incydent.
     * @param input Obiekt DTO incydentu.
     * @return Utworzony obiekt DTO incydentu lub wiadomość o błędzie.
     */
    @PostMapping({"/save", "/save/"})
    public ResponseEntity<?> saveIncydent(@RequestBody IncydentDto input) {
        try {
            var i = incydentService.saveIncydent(input);
            return ResponseEntity.ok(new IncydentDto(i));
        } catch (IncydentException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().eTag(e.getMessage()).build();
        }
    }

    /**
     * Aktualizuje incydent po identyfikatorze.
     * @param id Identyfikator incydentu.
     * @param input Obiekt DTO incydentu.
     * @return Status operacji lub wiadomość o błędzie.
     */
    @PatchMapping({"/{id}", "/{id}/"})
    public ResponseEntity<?> updateIncydent(@PathVariable(name = "id") Long id, @RequestBody IncydentDto input) {
        try {
            incydentService.updateIncydent(id, input);
            return ResponseEntity.ok().build();
        } catch (IncydentException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    /**
     * Pobiera incydent po identyfikatorze.
     * @param incydentId Identyfikator incydentu.
     * @return Obiekt DTO incydentu lub wiadomość o błędzie.
     */
    @GetMapping({"/{incydent_id}", "/{incydent_id}/"})
    public ResponseEntity<?> getIncydent(@PathVariable(name = "incydent_id") Long incydentId) {
        try {
            var incydent = incydentService.findIncydentById(incydentId).orElseThrow();
            IncydentDtoOutput output = new IncydentDtoOutput(incydent);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Usuwa incydent po identyfikatorze.
     * @param incydentId Identyfikator incydentu.
     * @return Status operacji lub wiadomość o błędzie.
     */
    @DeleteMapping({"/{incydent_id}", "/{incydent_id}/"})
    public ResponseEntity<?> deleteIncydent(@PathVariable(name = "incydent_id") Long incydentId) {
        try {
            incydentRepository.deleteById(incydentId);
            return ResponseEntity.ok("deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera wszystkie incydenty lub filtrowane po typie.
     * @param typ Typ incydentu (opcjonalny).
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping()
    public ResponseEntity<?> getIncydents(@RequestParam(name = "typ", required = false) String typ) {
        try {
            List<Incydent> incydents = typ == null ? incydentRepository.findAll() : incydentRepository.findAllByTyp(typ);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera incydenty przed określoną datą.
     * @param date Data w formacie dd.MM.yyyy.
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping("/beforeDate")
    public ResponseEntity<?> getIncydentsBeforeDate(@RequestParam(name = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        try {
            var incydents = incydentRepository.findAllByDateBefore(date);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera incydenty po określonej dacie.
     * @param date Data w formacie dd.MM.yyyy.
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping("/afterDate")
    public ResponseEntity<?> getIncydentsAfterDate(@RequestParam(name = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        try {
            var incydents = incydentRepository.findAllByDateAfter(date);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera incydenty pomiędzy dwiema datami.
     * @param startDate Data początkowa w formacie dd.MM.yyyy.
     * @param endDate Data końcowa w formacie dd.MM.yyyy.
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping("/betweenDates")
    public ResponseEntity<?> getIncydentsBetweenDates(@RequestParam(name = "startDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                                      @RequestParam(name = "endDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate) {
        try {
            var incydents = incydentRepository.findAllByDateBetween(startDate, endDate);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera incydenty po identyfikatorze autobusu.
     * @param autobusId Identyfikator autobusu.
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping("/byAutobus")
    public ResponseEntity<?> getIncydentsByAutobus(@RequestParam(name = "autobus_id") Long autobusId) {
        try {
            Autobus autobus = new Autobus(); // Assuming Autobus is an entity and you have a way to get it by ID
            autobus.setAutbousId(autobusId); // Set the ID to the Autobus object
            var incydents = incydentRepository.findAllByAutobus(autobus);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera incydenty po identyfikatorze kierowcy.
     * @param kierowcaId Identyfikator kierowcy.
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping("/byKierowca")
    public ResponseEntity<?> getIncydentsByKierowca(@RequestParam(name = "kierowca_id") Long kierowcaId) {
        try {
            Kierowca kierowca = new Kierowca(); // Assuming Kierowca is an entity and you have a way to get it by ID
            kierowca.setKierowcaId(kierowcaId); // Set the ID to the Kierowca object
            var incydents = incydentRepository.findAllByKierowca(kierowca);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera incydenty po identyfikatorze przejazdu.
     * @param przejazdId Identyfikator przejazdu.
     * @return Lista obiektów DTO incydentów.
     */
    @GetMapping("/byPrzejazd")
    public ResponseEntity<?> getIncydentsByPrzejazd(@RequestParam(name = "przejazd_id") Long przejazdId) {
        try {
            Przejazd przejazd = new Przejazd(); // Assuming Przejazd is an entity and you have a way to get it by ID
            przejazd.setPrzejazdId(przejazdId); // Set the ID to the Przejazd object
            var incydents = incydentRepository.findAllByPrzejazd(przejazd);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
