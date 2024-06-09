package com.mklachl.sopkom.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.helpers.Helpers;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.services.KierowcaService;
import com.mklachl.sopkom.services.UserService;
import com.mklachl.sopkom.services.impl.KierowcaServiceImpl;

/**
 * Kontroler dla zasobu Kierowca.
 * Obsługuje operacje tworzenia, pobierania, aktualizacji i usuwania kierowców.
 */
@RestController
@RequestMapping("/api/kierowca")
public class KierowcaController {

    KierowcaService kierowcaService;
    UserService userService;

    private KierowcaRepository kierowcaRepository;

    /**
     * Konstruktor kontrolera.
     * @param kierowcaRepository Repozytorium kierowców.
     * @param userService Serwis użytkowników.
     */
    public KierowcaController(KierowcaRepository kierowcaRepository, UserService userService) {
        this.kierowcaRepository = kierowcaRepository;
        kierowcaService = new KierowcaServiceImpl(kierowcaRepository, userService);
        this.userService = userService;
    }

    /**
     * Szablon obiektu KierowcaDto.
     * @return Obiekt KierowcaDto.
     */
    @GetMapping(path = {"template"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> template() {
        KierowcaDto dto = new KierowcaDto();
        dto.setKierowcaId(Long.valueOf(12));
        dto.setNazwisko("Kowalski");
        dto.setImie("Jan");
        dto.setPrawoJazdyWazneDo(new Date());
        dto.setPesel("93050203761");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Tworzy nowego kierowcę.
     * @param kierowca Obiekt DTO kierowcy.
     * @return Utworzony obiekt DTO kierowcy.
     */
    @PostMapping("/save")
    public ResponseEntity<KierowcaDto> saveKierowca(@RequestBody KierowcaDto kierowca) {
        System.out.println(kierowca);
        Kierowca savedKierowca = kierowcaService.saveKierowca(kierowca);
        KierowcaDto kierowcaDto = new KierowcaDto(savedKierowca);
        return new ResponseEntity<>(kierowcaDto, HttpStatus.CREATED);
    }

    /**
     * Pobiera kierowcę po identyfikatorze.
     * @param id Identyfikator kierowcy.
     * @return Obiekt DTO kierowcy lub wiadomość o błędzie.
     */
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<KierowcaDto> findKierowcaById(@PathVariable(name = "id") Long id) {
        Optional<Kierowca> optionalKierowca = kierowcaService.findKierowcaById(id);
        return optionalKierowca.map(kierowca -> ResponseEntity.ok(new KierowcaDto(kierowca)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Usuwa kierowcę po identyfikatorze.
     * @param id Identyfikator kierowcy.
     * @return Status operacji.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKierowca(@PathVariable("id") Long id) {
        if (kierowcaRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(new SimpleMessage("no content"), HttpStatus.OK);
        }
        kierowcaRepository.deleteById(id);
        return new ResponseEntity<>(new SimpleMessage("deleted"), HttpStatus.OK);
    }

    /**
     * Aktualizuje kierowcę po identyfikatorze.
     * @param id Identyfikator kierowcy.
     * @param kierowcaDto Obiekt DTO kierowcy.
     * @return Status operacji.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateKierowca(@PathVariable("id") Long id, @RequestBody KierowcaDto kierowcaDto) {
        Optional<Kierowca> keirowcaOp = kierowcaRepository.findById(id);
        if (keirowcaOp.isEmpty()) {
            return new ResponseEntity<>(new SimpleMessage("not found"), HttpStatus.NOT_FOUND);
        }

        Kierowca kierowca = keirowcaOp.get();
        kierowca.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
        kierowca.setImie(kierowcaDto.getImie());
        kierowca.setNazwisko(kierowcaDto.getNazwisko());
        kierowca.setPesel(kierowcaDto.getPesel());

        if (kierowcaDto.getUser() != null) {
            User userIn = userService.findUserById(kierowcaDto.getUser().getId());
            kierowca.setUser(userIn);
        }

        kierowca = kierowcaRepository.save(kierowca);
        return new ResponseEntity<>(new SimpleMessage("patched"), HttpStatus.OK);
    }

    /**
     * Pobiera kierowców po numerze PESEL.
     * @param pesel Numer PESEL (opcjonalny).
     * @return Lista obiektów DTO kierowców.
     */
    @GetMapping("/byPesel")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByPesel(@RequestParam(name = "pesel", required = false) String pesel) {
        List<KierowcaDto> kierowcyDto;

        if (pesel == null || pesel.isBlank() || pesel.equals("ALL")) {
            kierowcyDto = kierowcaRepository.findAll()
                    .stream()
                    .map(KierowcaDto::new)
                    .collect(Collectors.toList());
        } else {
            kierowcyDto = kierowcaRepository.findAllByPeselLike("%" + pesel + "%")
                    .stream()
                    .map(KierowcaDto::new)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(kierowcyDto, HttpStatus.OK);
    }

    /**
     * Pobiera kierowcę powiązanego z zalogowanym użytkownikiem.
     * @return Obiekt DTO kierowcy lub wiadomość o błędzie.
     */
    @GetMapping("/byUser/")
    public ResponseEntity<KierowcaDto> findKierowcaByUser() {
        Kierowca kierowca = kierowcaService.findKierowcaByUser(Helpers.getUserFromContext(userService));
        if (kierowca != null) {
            KierowcaDto kierowcaDto = new KierowcaDto(kierowca);
            return ResponseEntity.ok(kierowcaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Pobiera kierowców po imieniu i nazwisku.
     * @param imie Imię kierowcy.
     * @param nazwisko Nazwisko kierowcy.
     * @return Lista obiektów DTO kierowców.
     */
    @GetMapping("/byImieAndNazwisko")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByImieAndNazwisko(@RequestParam(name = "imie") String imie, @RequestParam(name = "nazwisko") String nazwisko) {
        List<Kierowca> kierowcy = kierowcaService.findKierowcaByImieAndNazwisko(imie, nazwisko);
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

    /**
     * Pobiera kierowców po nazwisku.
     * @param nazwisko Nazwisko kierowcy.
     * @return Lista obiektów DTO kierowców.
     */
    @GetMapping("/byNazwisko")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByNazwisko(@RequestParam(name = "nazwisko") String nazwisko) {
        List<Kierowca> kierowcy = kierowcaRepository.findByNazwiskoLike("%" + nazwisko + "%");
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

    /**
     * Pobiera kierowców z prawem jazdy ważnym do określonej daty.
     * @param date Data ważności prawa jazdy.
     * @return Lista obiektów DTO kierowców.
     */
    @GetMapping("/byPrawoJazdyWazneDoBefore")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByPrawoJazdyWazneDoBefore(@RequestParam(name = "date") Date date) {
        List<Kierowca> kierowcy = kierowcaService.findKierowcaByPrawoJazdyWazneDoBefore(date);
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

    /**
     * Pobiera kierowców z prawem jazdy ważnym po określonej dacie.
     * @param date Data ważności prawa jazdy.
     * @return Lista obiektów DTO kierowców.
     */
    @GetMapping("/byPrawoJazdyWazneDoAfter")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByPrawoJazdyWazneDoAfter(@RequestParam(name = "date") Date date) {
        List<Kierowca> kierowcy = kierowcaService.findKierowcaByPrawoJazdyWazneDoAfter(date);
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

    /**
     * Pobiera wszystkich kierowców.
     * @return Lista obiektów DTO kierowców.
     */
    @GetMapping(path = {"/", ""})
    public ResponseEntity<List<KierowcaDto>> findAll() {
        List<Kierowca> kierowcy = kierowcaRepository.findAll();
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }
}
