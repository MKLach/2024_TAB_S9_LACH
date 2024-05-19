package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;

import java.util.List;
import java.util.Optional;

public interface HarmonogramService {

    Harmonogram saveHarmonogram(Harmonogram harmonogram);

    Harmonogram saveHarmonogram(HarmonogramDto harmonogramDto);

    Optional<Harmonogram> findHarmonogramById(Short id);

    List<Harmonogram> findHarmonogramByNazwa(String nazwa);

    List<Harmonogram> findHarmonogramByPon(boolean pon);

    List<Harmonogram> findHarmonogramByWto(boolean wto);

    List<Harmonogram> findHarmonogramBySro(boolean sro);

    List<Harmonogram> findHarmonogramByCzw(boolean czw);

    List<Harmonogram> findHarmonogramByPia(boolean pia);

    List<Harmonogram> findHarmonogramBySob(boolean sob);

    List<Harmonogram> findHarmonogramByNie(boolean nie);

    List<Harmonogram> findHarmonogramByDodatkoweInfLike(String dodatkoweInf);

}
