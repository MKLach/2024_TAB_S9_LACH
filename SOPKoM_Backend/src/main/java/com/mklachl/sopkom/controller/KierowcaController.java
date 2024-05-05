package com.mklachl.sopkom.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.mklachl.sopkom.helpers.Helpers;
import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.UserRepository;
import com.mklachl.sopkom.services.KierowcaService;
import com.mklachl.sopkom.services.UserService;
import com.mklachl.sopkom.services.impl.KierowcaServiceImpl;
import com.mklachl.sopkom.services.impl.UserServiceImpl;


/**
 * - POST /api/kierowcy/save - add
 * - GET /api/kierowcy/byPesel/?pesel=...
 * - GET /api/kierowcy/byUser/{userId}
 * - GET /api/kierowcy/byImieAndNazwisko?imie={imie}&nazwisko={nazwisko}
 * - GET /api/kierowcy/byNazwisko?nazwisko={nazwisko} - filtry do tego!
 * - GET /api/kierowcy/byPrawoJazdyWazneDoBefore?date={date} - jak wam się chce
 * - GET /api/kierowcy/byPrawoJazdyWazneDoAfter?date={date} - jak wam się chce
 * - GET /api/kierowcy/{id} - get spec
 * - PATCH /api/kierowcy/{id} - update
 * - DELETE /api/kierowcy/{id} - del
 * - GET /api/kierowcy/ - all
 */
@RestController
@RequestMapping("/api/kierowca")
public class KierowcaController {

	
    KierowcaService kierowcaService;
    UserService userService;
    
    private KierowcaRepository kierowcaRepository;

    public KierowcaController(KierowcaRepository kierowcaRepository, UserService userService) {
    	this.kierowcaRepository = kierowcaRepository;
    	kierowcaService = new KierowcaServiceImpl(kierowcaRepository, userService);
    	this.userService = userService;
	}
    
   // @Autowired
   // private UserService userService;
   // 
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
    
    @PostMapping("/save")
    public ResponseEntity<KierowcaDto> saveKierowca(@RequestBody KierowcaDto kierowca) {
    	System.out.println(kierowca);
        Kierowca savedKierowca = kierowcaService.saveKierowca(kierowca);
        KierowcaDto kierowcaDto = new KierowcaDto(savedKierowca);
        return new ResponseEntity<>(kierowcaDto, HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})	
    public ResponseEntity<KierowcaDto> findKierowcaById(@PathVariable(name="id") Long id) {
        Optional<Kierowca> optionalKierowca = kierowcaService.findKierowcaById(id);
        
        
        
        return optionalKierowca.map(kierowca -> ResponseEntity.ok(new KierowcaDto(kierowca)))
                .orElse(ResponseEntity.notFound().build());
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteKierowca(@PathVariable("id") Long id) {
		
		if(kierowcaRepository.findById(id).isEmpty()) {
			return new ResponseEntity<>(new SimpleMessage("no content"), HttpStatus.OK);
		}
		
		kierowcaRepository.deleteById(id);
		
		return new ResponseEntity<>(new SimpleMessage("deleted"), HttpStatus.OK);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateKierowca(@PathVariable("id") Long id, @RequestBody KierowcaDto kierowcaDto) {
		
		Optional<Kierowca> keirowcaOp = kierowcaRepository.findById(id);
		
		if(keirowcaOp.isEmpty()) {
			return new ResponseEntity<>(new SimpleMessage("not found"), HttpStatus.NOT_FOUND);
		}
		
		Kierowca kierowca = keirowcaOp.get();
		
		
		kierowca.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
        kierowca.setImie(kierowcaDto.getImie());
        kierowca.setNazwisko(kierowcaDto.getNazwisko());
        kierowca.setPesel(kierowcaDto.getPesel());
        
        if(kierowcaDto.getUser() != null) {
        	 User userIn = userService.findUserById(kierowcaDto.getUser().getId());
        	 kierowca.setUser(userIn);

        }
        
        kierowca = kierowcaRepository.save(kierowca);
		
		
		//if(Strings.isNotBlank(autobusdto.getNumerRejestracyjny())) {
		//in.setImie(kierowcaDto.getImie());
			
		//}
		
		//if(Strings.isNotBlank(autobusdto.getStatus())) {
		//in.setNazwisko(kierowcaDto.getNazwisko());
			
		//}
		
		//if(Strings.isNotBlank(autobusdto.getStatus())) {
		//in.setPesel(kierowcaDto.getPesel());
			
		//}
		
		//if(Strings.isNotBlank(autobusdto.getPrzebieg())) {
		//in.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
			
		//}
			
		//if(kierowcaDto.getUser() != null) {
			
			
		//}
		
		
			
			
		//in = autobusRepo.save(in);
		
		//autobusdto = new AutobusDto(in);
		
		return new ResponseEntity<>(new SimpleMessage("patched"), HttpStatus.OK);
		
	}

    @GetMapping("/byPesel")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByPesel(@RequestParam(name = "pesel",required = false) String pesel) {
    	//List<Kierowca> kierowcy = kierowcaRepository.findAllByPeselLike("%"+pesel+"%");
    
    	List<KierowcaDto> kierowcyDto = null;
          
		  
    	if(pesel == null || pesel.isBlank() || pesel.equals("ALL")) {
			
			  kierowcyDto =  kierowcaRepository.findAll()
					  .stream()
					  .map(KierowcaDto::new)
					  .collect(Collectors.toList());
			
			
		} else {
			
			 kierowcyDto = kierowcaRepository.findAllByPeselLike("%" + pesel + "%")
					 .stream()
				     .map(KierowcaDto::new)
				     .collect(Collectors.toList());
					
			
		}
  		
          return new ResponseEntity<List<KierowcaDto>>(kierowcyDto, HttpStatus.OK);
    }

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

    @GetMapping("/byImieAndNazwisko")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByImieAndNazwisko(@RequestParam(name = "imie") String imie, @RequestParam(name = "nazwisko") String nazwisko) {
        List<Kierowca> kierowcy = kierowcaService.findKierowcaByImieAndNazwisko(imie, nazwisko);
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }
    
    @GetMapping("/byNazwisko")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByNazwisko(@RequestParam(name = "nazwisko") String nazwisko) {
        List<Kierowca> kierowcy = kierowcaRepository.findByNazwiskoLike("%"+nazwisko+"%");
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

    @GetMapping("/byPrawoJazdyWazneDoBefore")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByPrawoJazdyWazneDoBefore(@RequestParam(name = "date") Date date) {
        List<Kierowca> kierowcy = kierowcaService.findKierowcaByPrawoJazdyWazneDoBefore(date);
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

    @GetMapping("/byPrawoJazdyWazneDoAfter")
    public ResponseEntity<List<KierowcaDto>> findKierowcaByPrawoJazdyWazneDoAfter(@RequestParam(name = "date") Date date) {
        List<Kierowca> kierowcy = kierowcaService.findKierowcaByPrawoJazdyWazneDoAfter(date);
        List<KierowcaDto> kierowcyDto = kierowcy.stream()
                .map(KierowcaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kierowcyDto);
    }

 
    
    @GetMapping(path = {"/", ""})
    public ResponseEntity<List<KierowcaDto>> findAll() {
    	  List<Kierowca> kierowcy = kierowcaRepository.findAll();
          List<KierowcaDto> kierowcyDto = kierowcy.stream()
                  .map(KierowcaDto::new)
                  .collect(Collectors.toList());
          return ResponseEntity.ok(kierowcyDto);
    }
    
    
}

