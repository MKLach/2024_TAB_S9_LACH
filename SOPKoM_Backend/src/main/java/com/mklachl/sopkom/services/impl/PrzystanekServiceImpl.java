package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.repository.PrzystanekRepository;
import com.mklachl.sopkom.services.PrzystanekService;

import java.util.List;
import java.util.Optional;

public class PrzystanekServiceImpl implements PrzystanekService {
    private PrzystanekRepository przystanekRepository;

    public PrzystanekServiceImpl(PrzystanekRepository przystanekRepository) {this.przystanekRepository = przystanekRepository;}

    @Override
    public Przystanek savePrzystanek(Przystanek przystanekDto) {
        Przystanek przystanek = new Przystanek();
        przystanek.setPrzystanekOdwrotny(przystanekDto.getPrzystanekOdwrotny());
        przystanek.setNazwa(przystanekDto.getNazwa());
        przystanek.setKodPocztowy(przystanekDto.getKodPocztowy());
        przystanek.setMiasto(przystanekDto.getMiasto());
        przystanek.setUlica(przystanekDto.getUlica());
        przystanek.setDlugoscGeograficzna(przystanekDto.getDlugoscGeograficzna());
        przystanek.setSzerokoscGeograficzna(przystanekDto.getSzerokoscGeograficzna());

        przystanek = przystanekRepository.save(przystanek);
        return przystanek;
    }

    public Przystanek savePrzystanek(PrzystanekDto przystanekDto) {
        Przystanek przystanek = new Przystanek();
        przystanek.setPrzystanekOdwrotny(przystanekDto.getPrzystanekOdwrotny());
        przystanek.setNazwa(przystanekDto.getNazwa());
        przystanek.setKodPocztowy(przystanekDto.getKodPocztowy());
        przystanek.setMiasto(przystanekDto.getMiasto());
        przystanek.setUlica(przystanekDto.getUlica());
        przystanek.setDlugoscGeograficzna(przystanekDto.getDlugoscGeograficzna());
        przystanek.setSzerokoscGeograficzna(przystanekDto.getSzerokoscGeograficzna());

        przystanek = przystanekRepository.save(przystanek);
        return przystanek;
    }

    @Override
    public Przystanek findPrzystanekByNazwa(String nazwa){
        return przystanekRepository.findByNazwa(nazwa);
    }

    @Override
    public List<Przystanek> findPrzystanekByKodPocztowy(String kodPocztowy){
        return przystanekRepository.findAllByKodPocztowy(kodPocztowy);
    }

    @Override
    public List<Przystanek> findPrzystanekByMiasto(String miasto){
        return przystanekRepository.findAllByMiasto(miasto);
    }

    @Override
    public List<Przystanek> findPrzystanekByUlica(String ulica){
        return przystanekRepository.findAllByUlica(ulica);
    }

    @Override
    public List<Przystanek> findPrzystanekBySzerokoscGeograficzna(String szerokoscGeograficzna){
        return przystanekRepository.findAllBySzerokoscGeograficzna(szerokoscGeograficzna);
    }

    @Override
    public List<Przystanek> findPrzystanekByDlugoscGeograficzna(String dlugoscGeograficzna){
        return przystanekRepository.findAllByDlugoscGeograficzna(dlugoscGeograficzna);
    }

    @Override
    public Optional<Przystanek> findPrzystanekById(Long id){
        return przystanekRepository.findById(id);
    }

    @Override
    public List<Przystanek> findPrzystanekByPrzystanekOdwrotny(Przystanek przystanekOdwrotny){
        return przystanekRepository.findAllByPrzystanekOdwrotny(przystanekOdwrotny);
    }

}
