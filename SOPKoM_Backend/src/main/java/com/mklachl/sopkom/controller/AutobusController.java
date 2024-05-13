package com.mklachl.sopkom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.services.AutobusService;


/***
 *    /api/autobus/save - creates new , POST
 *    /api/autobus/{id} - gets the bus by given id. GET
 *    /api/autobus/{id} - gets the bus by given id. PATCH
 *    /api/autobus/{id} - deletes the bus by given id. DELETE
 *    /api/autobus/ - get all present
 *    /api/autobus/filtered - only by status at this moment, GET
 */
@RestController
@RequestMapping("api/autobus")
public class AutobusController {

	
	@Autowired
	AutobusService autobusService;
	
	@Autowired
	AutobusRepository autobusRepo;
	
	@GetMapping(path = {"test"}, produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> test() {
		
		return new ResponseEntity<>("test_autobus", HttpStatus.OK);
		
	}
	
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
	
	@PostMapping("/save")
	public ResponseEntity<?> addAutobus(@RequestBody AutobusDto autobusDto){

		var autobus =  autobusService.saveAutobus(autobusDto);

		return new ResponseEntity<>(new AutobusDto(autobus), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @param id
	 * @return 404 - not found if not present, 200 and Autobus DTO if present
	 */
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})	
	public ResponseEntity<?> getAutobus(@PathVariable("id") Long id){
		
		var autobus = autobusService.findAutobusById(id.longValue());
		
		if(autobus.isEmpty()) {
			
			return new ResponseEntity<Object>(new SimpleMessage("not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new AutobusDto(autobus.get()), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAutobus(@PathVariable("id") Long id) {
		
		if(autobusRepo.findById(id).isEmpty()) {
			return new ResponseEntity<>(new SimpleMessage("no content"), HttpStatus.OK);
		}
		
		autobusRepo.deleteById(id);
		
		return new ResponseEntity<>(new SimpleMessage("deleted"), HttpStatus.OK);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateAutobus(@PathVariable("id") Long id, @RequestBody AutobusDto autobusdto) {
		
		Optional<Autobus> autobus = autobusRepo.findById(id);
		
		if(autobus.isEmpty()) {
			return new ResponseEntity<>(new SimpleMessage("not found"), HttpStatus.NOT_FOUND);
		}
		
		Autobus in = autobus.get();
		
		
		
		
		
		//if(Strings.isNotBlank(autobusdto.getNumerRejestracyjny())) {
		in.setNumerRejestracyjny(autobusdto.getNumerRejestracyjny());
			
		//}
		
		//if(Strings.isNotBlank(autobusdto.getStatus())) {
		in.setStatus(autobusdto.getStatus());
			
		//}
		
		//if(Strings.isNotBlank(autobusdto.getStatus())) {
		in.setStatus(autobusdto.getStatus());
			
		//}
		
		//if(Strings.isNotBlank(autobusdto.getPrzebieg())) {
		in.setPrzebieg(autobusdto.getPrzebieg());
			
		//}
			
		in.setPrzegladWaznyDo(autobusdto.getPrzegladWaznyDo());
			
			
		in = autobusRepo.save(in);
		
		autobusdto = new AutobusDto(in);
		
		return new ResponseEntity<>(new SimpleMessage("patched"), HttpStatus.OK);
		
	}
	
	@GetMapping(path = {"", "/"})
    public ResponseEntity<?> getAllBuses() {
		
		List<AutobusDto> list = new ArrayList<AutobusDto>();
		autobusRepo.findAll().forEach((autobus) -> {
			
			list.add(new AutobusDto(autobus));
			
		});
		
        return new ResponseEntity<List<AutobusDto>>(list, HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/filtered"})
    public ResponseEntity<?> getAllBusesFilteredStatus(@RequestParam(name = "status",required = false) String status) {
		List<AutobusDto> list = new ArrayList<AutobusDto>();
		
		if(status == null || status.isBlank() || status.equals("ALL")) {
			
			autobusRepo.findAll().forEach((autobus) -> {
				
				list.add(new AutobusDto(autobus));
				
			});
			
			
		} else {
			//System.out.println(status);
			autobusRepo.findByStatusLike("%" + status + "%").forEach((autobus) -> {
				
				list.add(new AutobusDto(autobus));
				
			});
			
		}
		
		
        return new ResponseEntity<List<AutobusDto>>(list, HttpStatus.OK);
    }
	
	
	
}
