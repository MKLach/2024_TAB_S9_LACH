package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.services.KursService;

import java.util.List;

public class KursServiceImpl implements KursService {

    private KursRepository kursRepository;

    public KursServiceImpl(KursRepository kursRepository) {this.kursRepository = kursRepository;}

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

    public Kurs saveKurs(KursDto kursDto){
        Kurs kurs = new Kurs();
        /*kurs.setLinia(kursDto.getLinia());
        kurs.setHarmonogram(kursDto.getHarmonogram());
        kurs.setKierunek(kursDto.getKierunek());
        kurs.setTypAutobusu(kursDto.getTypAutobusu());

        kurs = kursRepository.save(kurs);*/
        return kurs;
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
