package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.repository.HarmonogramRepository;
import com.mklachl.sopkom.services.HarmonogramService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class HarmonogramServiceImpl implements HarmonogramService {
    private HarmonogramRepository harmonogramRepository;

    public HarmonogramServiceImpl(HarmonogramRepository harmonogramRepository) {this.harmonogramRepository = harmonogramRepository;}

    @Override
    public Harmonogram saveHarmonogram(Harmonogram harmonogramDto){
        Harmonogram harmonogram = new Harmonogram();
        harmonogram.setNazwa(harmonogramDto.getNazwa());
        harmonogram.setPon(harmonogramDto.isPon());
        harmonogram.setWto(harmonogramDto.isWto());
        harmonogram.setSro(harmonogramDto.isSro());
        harmonogram.setCzw(harmonogramDto.isCzw());
        harmonogram.setPia(harmonogramDto.isPia());
        harmonogram.setSob(harmonogramDto.isSob());
        harmonogram.setNie(harmonogramDto.isNie());
        harmonogram.setDodatkoweInf(harmonogramDto.getDodatkoweInf());

        harmonogram = harmonogramRepository.save(harmonogram);
        return harmonogram;
    }

    public Harmonogram saveHarmonogram(HarmonogramDto harmonogramDto){
        Harmonogram harmonogram = new Harmonogram();
        harmonogram.setNazwa(harmonogramDto.getNazwa());
        harmonogram.setPon(harmonogramDto.isPon());
        harmonogram.setWto(harmonogramDto.isWto());
        harmonogram.setSro(harmonogramDto.isSro());
        harmonogram.setCzw(harmonogramDto.isCzw());
        harmonogram.setPia(harmonogramDto.isPia());
        harmonogram.setSob(harmonogramDto.isSob());
        harmonogram.setNie(harmonogramDto.isNie());
        harmonogram.setDodatkoweInf(harmonogramDto.getDodatkoweInf());

        harmonogram = harmonogramRepository.save(harmonogram);
        return harmonogram;
    }

    @Override
    public Optional<Harmonogram> findHarmonogramById(Short id){return harmonogramRepository.findById(id);}

    @Override
    public List<Harmonogram> findHarmonogramByNazwa(String nazwa){return harmonogramRepository.findAllByNazwa(nazwa);}

    @Override
    public List<Harmonogram> findHarmonogramByPon(boolean pon){return harmonogramRepository.findAllByPon(pon);}

    @Override
    public List<Harmonogram> findHarmonogramByWto(boolean wto){return harmonogramRepository.findAllByWto(wto);}

    @Override
    public List<Harmonogram> findHarmonogramBySro(boolean sro){return harmonogramRepository.findAllBySro(sro);}

    @Override
    public List<Harmonogram> findHarmonogramByCzw(boolean czw){return harmonogramRepository.findAllByCzw(czw);}

    @Override
    public List<Harmonogram> findHarmonogramByPia(boolean pia){return harmonogramRepository.findAllByPia(pia);}

    @Override
    public List<Harmonogram> findHarmonogramBySob(boolean sob){return harmonogramRepository.findAllBySob(sob);}

    @Override
    public List<Harmonogram> findHarmonogramByNie(boolean nie){return harmonogramRepository.findAllByNie(nie);}

    @Override
    public List<Harmonogram> findHarmonogramByDodatkoweInfLike(String dodatkoweInf){return harmonogramRepository.findAllByDodatkoweInfLike(dodatkoweInf);}
}
