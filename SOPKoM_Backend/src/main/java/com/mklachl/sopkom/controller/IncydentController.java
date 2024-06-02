package com.mklachl.sopkom.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.exceptions.IncydentException;
import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.model.dto.IncydentDto;
import com.mklachl.sopkom.model.dto.IncydentDtoOutput;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.repository.IncydentRepository;
import com.mklachl.sopkom.services.IncydentService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/incydent")
public class IncydentController {

    @Autowired
    public IncydentService incydentService;
    
    @Autowired
    public IncydentRepository incydentRepository;

    @PostMapping({"/save", "/save/"})
    public ResponseEntity<?> saveIncydent(@RequestBody IncydentDto input){
        try {
			var i =  incydentService.saveIncydent(input);
			
			return ResponseEntity.ok(new IncydentDto(i));
			
		} catch (IncydentException e) {
			
			e.printStackTrace();
			return ResponseEntity.notFound().eTag(e.getMessage()).build();
		} catch(Exception e ){
			return ResponseEntity.internalServerError().eTag(e.getMessage()).build();
		}
       
    }
    
    @PatchMapping({"/{id}", "/{id}/"})
    public ResponseEntity<?> updateIncydent(@PathVariable(name = "id") Long id, @RequestBody IncydentDto input){
        try {
			incydentService.updateIncydent(id, input);
			
			return ResponseEntity.ok().build();
		} catch (IncydentException e) {
			
			e.printStackTrace();
			return ResponseEntity.notFound().eTag(e.getMessage()).build();
		}
       
    }
    
    @GetMapping({"/{incydent_id}", "/{incydent_id}/"})
    public ResponseEntity<?> getIncydent(@PathVariable(name="incydent_id") Long incydentId){
    	try {
            var incydent = incydentService.findIncydentById(incydentId).orElseThrow();

            IncydentDtoOutput output = new IncydentDtoOutput(incydent);
            
            return ResponseEntity.ok(output);
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @DeleteMapping({"/{incydent_id}", "/{incydent_id}/"})
    public ResponseEntity<?> deleteIncydent(@PathVariable(name="incydent_id") Long incydentId){
        try {
            incydentRepository.deleteById(incydentId);
            return ResponseEntity.ok("deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping()
    public ResponseEntity<?> getIncydents(@RequestParam(name="typ", required = false) String typ){
        try {
            List<Incydent> incydents = typ == null ? incydentRepository.findAll() : incydentRepository.findAllByTyp(typ);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/beforeDate")
    public ResponseEntity<?> getIncydentsBeforeDate(@RequestParam(name="date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        try {
            var incydents = incydentRepository.findAllByDateBefore(date);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/afterDate")
    public ResponseEntity<?> getIncydentsAfterDate(@RequestParam(name="date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        try {
            var incydents = incydentRepository.findAllByDateAfter(date);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/betweenDates")
    public ResponseEntity<?> getIncydentsBetweenDates(@RequestParam(name="startDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                                      @RequestParam(name="endDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate){
        try {
            var incydents = incydentRepository.findAllByDateBetween(startDate, endDate);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byAutobus")
    public ResponseEntity<?> getIncydentsByAutobus(@RequestParam(name="autobus_id") Long autobusId){
        try {
            Autobus autobus = new Autobus();  // Assuming Autobus is an entity and you have a way to get it by ID
            autobus.setAutbousId(autobusId);  // Set the ID to the Autobus object
            var incydents = incydentRepository.findAllByAutobus(autobus);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byKierowca")
    public ResponseEntity<?> getIncydentsByKierowca(@RequestParam(name="kierowca_id") Long kierowcaId){
        try {
            Kierowca kierowca = new Kierowca();  // Assuming Kierowca is an entity and you have a way to get it by ID
            kierowca.setKierowcaId(kierowcaId);  // Set the ID to the Kierowca object
            var incydents = incydentRepository.findAllByKierowca(kierowca);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byPrzejazd")
    public ResponseEntity<?> getIncydentsByPrzejazd(@RequestParam(name="przejazd_id") Long przejazdId){
        try {
            Przejazd przejazd = new Przejazd();  // Assuming Przejazd is an entity and you have a way to get it by ID
            przejazd.setPrzejazdId(przejazdId);  // Set the ID to the Przejazd object
            var incydents = incydentRepository.findAllByPrzejazd(przejazd);
            return ResponseEntity.ok(incydents.stream().map(IncydentDtoOutput::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
