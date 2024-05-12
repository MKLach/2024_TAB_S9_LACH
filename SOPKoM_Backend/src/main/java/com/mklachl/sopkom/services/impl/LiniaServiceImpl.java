package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;
import com.mklachl.sopkom.exceptions.LiniaNotFoundException;
import com.mklachl.sopkom.exceptions.PrzystanekNotFoundException;
import com.mklachl.sopkom.model.dto.LiniaDtoOutput;
import com.mklachl.sopkom.model.dto.LiniaDtoInput;
import com.mklachl.sopkom.model.dto.PrzystanekDtoDlaLiniaInput;
import com.mklachl.sopkom.repository.LiniaRepository;
import com.mklachl.sopkom.repository.PrzystanekRepository;
import com.mklachl.sopkom.repository.PrzystanekWliniRepository;
import com.mklachl.sopkom.services.LiniaService;
import com.mklachl.sopkom.services.PrzystanekService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LiniaServiceImpl implements LiniaService {
    @Autowired
    private LiniaRepository liniaRepository;

    @Autowired
    private PrzystanekRepository przystanekRepository;

    @Autowired
    private PrzystanekWliniRepository przystanekWLiniRepository;
    
    
    @Autowired
    private PrzystanekService przystanekService;
    
    
    
    @Override
    @Transactional(rollbackOn = {PrzystanekNotFoundException.class, LiniaNotFoundException.class})
    public Linia saveLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException {
      //  Linia linia = mapToEntity(liniaDto);
      // linia = liniaRepository.save(linia);
    	
    	Linia nowa = mapToEntity(liniaDto);
    	
    	nowa = liniaRepository.save(nowa);
    	
    	List<PrzystanekWlini> przystankiIn = new ArrayList<PrzystanekWlini>();
    	
    	
    	
    	for(int i = 0; i < liniaDto.getPrzystanki().size(); i++) {
    		
    		PrzystanekDtoDlaLiniaInput przystanekRaw = liniaDto.getPrzystanki().get(i);
    		
    		var przystanek = przystanekService.findPrzystanekById(przystanekRaw.getPrzystanekId());
    		if(przystanek.isPresent()) {
    			
    			PrzystanekWlini lacznik = new PrzystanekWlini();
    			lacznik.setKolejnosc(przystanekRaw.getKolejnosc());
    			lacznik.setLinia(nowa);
    			lacznik.setPrzystanek(przystanek.get());
    				
    			przystanekRepository.save(przystanek.get());
    			
    			przystankiIn.add(przystanekWLiniRepository.save(lacznik));
    			
    			
    		} else {
    			throw new PrzystanekNotFoundException(nowa.getLiniaId(), przystanekRaw.getPrzystanekId());
    		}
    
    	}
    	
    	nowa.setPrzystankiWlini(przystankiIn);
    	
        return liniaRepository.save(nowa);
    }
    
    
    @Override
    @Transactional(rollbackOn = {PrzystanekNotFoundException.class, LiniaNotFoundException.class})
    public Linia patchLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException, LiniaNotFoundException {
      
    	var ist = liniaRepository.findById(liniaDto.getId());
    	
    	if(ist.isEmpty()) {
    		throw new LiniaNotFoundException(liniaDto.getId());
    	}
    	
    	Linia nowa = ist.get();
    	
    	List<PrzystanekWlini> przystankiIn = new ArrayList<PrzystanekWlini>();
    	
    	for(int i = 0; i < liniaDto.getPrzystanki().size(); i++) {
    		
    		PrzystanekDtoDlaLiniaInput przystanekRaw = liniaDto.getPrzystanki().get(i);
    		
    		var przystanek = przystanekService.findPrzystanekById(przystanekRaw.getPrzystanekId());
    		if(przystanek.isPresent()) {
    			
    			PrzystanekWlini lacznik = new PrzystanekWlini();
    			lacznik.setKolejnosc(przystanekRaw.getKolejnosc());
    			lacznik.setLinia(nowa);
    			lacznik.setPrzystanek(przystanek.get());
    				
    			przystanekRepository.save(przystanek.get());
    			
    			przystankiIn.add(przystanekWLiniRepository.save(lacznik));
    			
    			
    		} else {
    			throw new PrzystanekNotFoundException(nowa.getLiniaId(), przystanekRaw.getPrzystanekId());
    		}
    
    	}
    	
    	nowa.setPrzystankiWlini(przystankiIn);
    	
        return liniaRepository.save(nowa);
    }

    @Override
    public void deleteLinia(Long id) {
        liniaRepository.deleteById(id);
    }

    @Override
    public Optional<Linia> findLiniaById(Long id) {
        return liniaRepository.findById(id);
    }

    @Override
    public Linia findLiniaByNumer(String numer) {
        return liniaRepository.findByNumer(numer);
    }

    private LiniaDtoOutput mapToDto(Linia linia) {
        LiniaDtoOutput dto = new LiniaDtoOutput();
        dto.setId(linia.getLiniaId());
        dto.setNumer(linia.getNumer());
        return dto;
    }

    private Linia mapToEntity(LiniaDtoOutput dto) {
        Linia linia = new Linia();
        linia.setLiniaId(dto.getId());
        linia.setNumer(dto.getNumer());
        return linia;
    }
    
   

    private Linia mapToEntity(LiniaDtoInput dto) {
        Linia linia = new Linia();
        linia.setLiniaId(dto.getId());
        linia.setNumer(dto.getNumer());
        return linia;
    }
}

