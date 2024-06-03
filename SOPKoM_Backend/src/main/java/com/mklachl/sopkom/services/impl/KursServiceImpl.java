package com.mklachl.sopkom.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mklachl.sopkom.model.dto.kurs.AbstractKursDto;
import com.mklachl.sopkom.model.dto.kurs.InputKursDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;
import com.mklachl.sopkom.raporty.rozkład.DateHelper;
import com.mklachl.sopkom.repository.KursPrzystanekWliniRepository;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.repository.PrzystanekWliniRepository;
import com.mklachl.sopkom.services.HarmonogramService;
import com.mklachl.sopkom.services.KursService;
import com.mklachl.sopkom.services.LiniaService;

@Service
public class KursServiceImpl implements KursService {

	
    private KursRepository kursRepository;

    private LiniaService liniaService;
    
    private HarmonogramService harmonogramService;
    
    private KursPrzystanekWliniRepository kpwlRepository;
    
    private PrzystanekWliniRepository pwlRepository;
    
    public KursServiceImpl(KursRepository kursRepository, LiniaService liniaService,
			HarmonogramService harmonogramService, KursPrzystanekWliniRepository kpwlRepository,
			PrzystanekWliniRepository pwlRepository) {
		super();
		this.kursRepository = kursRepository;
		this.liniaService = liniaService;
		this.harmonogramService = harmonogramService;
		this.kpwlRepository = kpwlRepository;
		this.pwlRepository = pwlRepository;
	}

	@Override
    public Kurs saveKurs(Kurs kursDto){
        Kurs kurs = new Kurs();
        kurs.setLinia(kursDto.getLinia());
        kurs.setHarmonogram(kursDto.getHarmonogram());
        kurs.setKierunek(kursDto.getKierunek());
        kurs.setTypAutobusu(kursDto.getTypAutobusu());

        
        
        
        
        
        
        
        
        
        kurs = kursRepository.save(kurs);
        return kurs;
    }

	@jakarta.transaction.Transactional(rollbackOn = {Exception.class})
    public Kurs saveKurs(InputKursDto kursDto) throws Exception{
        Kurs kurs = new Kurs();
           
      
        Optional<Linia> linia = liniaService.findLiniaById(kursDto.getLiniaId());
        
        if(linia.isEmpty()) {
        	throw new Exception("linia not found!");
        }
        
        Optional<Harmonogram> harmonogram = harmonogramService.findHarmonogramById(kursDto.getHarmonogramId());
        
        if(harmonogram.isEmpty()) {
        	throw new Exception("harmonogram not found!");
        }
        
        kurs.setLinia(linia.get());
        kurs.setHarmonogram(harmonogram.get());
        kurs.setKierunek(kursDto.getKierunek());
        kurs.setTypAutobusu(kursDto.getTyp_autobusu());
        kurs = kursRepository.save(kurs);
        
        var data = new ArrayList<KursPrzystanekWlini>();
        
        
        for(var prz: kursDto.getPrzystanki()) {
        	KursPrzystanekWlini kpwl = new KursPrzystanekWlini();
        	PrzystanekWlini pwl = pwlRepository.findById(prz.getPrzystanekWLini().getPrzystanekwliniId()).get();
        	
        	kpwl.setGodzinna(prz.getGodzinna());
        	kpwl.setPrzystanekWlini(pwl);
        	kpwl.setKurs(kurs);
        	kpwl = kpwlRepository.save(kpwl);
        	
        	data.add(kpwl);
        }
       
        kurs.setKursPrzystanekWlinii(data);
        kurs = kursRepository.save(kurs);
        
        return kurs;
    }

	@jakarta.transaction.Transactional(rollbackOn = {Exception.class})
    public Kurs updateKurs(InputKursDto kursDto) throws Exception {
		
        var kursO = kursRepository.findById(kursDto.getKursId());
       
        if(kursO.isEmpty()) {
        	throw new Exception("kurs not found!");
        }  
        
        Optional<Linia> linia = liniaService.findLiniaById(kursDto.getLiniaId());
        
        if(linia.isEmpty()) {
        	throw new Exception("linia not found!");
        }
        
        Optional<Harmonogram> harmonogram = harmonogramService.findHarmonogramById(kursDto.getHarmonogramId());
        
        if(harmonogram.isEmpty()) {
        	throw new Exception("harmonogram not found!");
        }
        
        Kurs kurs = kursO.get();
        
        kurs.setLinia(linia.get());
        kurs.setHarmonogram(harmonogram.get());
        kurs.setKierunek(kursDto.getKierunek());
        kurs.setTypAutobusu(kursDto.getTyp_autobusu());
        kurs = kursRepository.save(kurs);
        
        //var data = new ArrayList<KursPrzystanekWlini>();
        
        for(int i = 0; i < kursDto.getPrzystanki().size(); i++) {
        	var dtoIn = kursDto.getPrzystanki().get(i);
        	KursPrzystanekWlini kpwl = kurs.getKursPrzystanekWlinii().stream().filter((k) -> k.getKursPrzystanekWliniId().equals(dtoIn.getPrzystanekwKursieId())).findFirst().get();
        	
        	kpwl.setGodzinna(dtoIn.getGodzinna());
        	PrzystanekWlini pwl = pwlRepository.findById(dtoIn.getPrzystanekWLini().getPrzystanekwliniId()).get();
        	
        	kpwl.setPrzystanekWlini(pwl);
        	
        	kpwl = kpwlRepository.save(kpwl);
        }
        
        
        
        return kursRepository.findById(kurs.getKursId()).get();
    }

    @Override
    public List<Kurs> findKursByLinia(Linia linia){return kursRepository.findAllByLinia(linia);}

    @Override
    public List<Kurs> findKursByHarmonogram(Harmonogram harmonogram){return kursRepository.findAllByHarmonogram(harmonogram);}

    @Override
    public List<Kurs> findKursByKierunek(Short kierunek){return kursRepository.findAllByKierunek(kierunek);}

    @Override
    public List<Kurs> findKursByTypAutobusu(Short typAutobusu){return kursRepository.findAllByTypAutobusu(typAutobusu);}


}
