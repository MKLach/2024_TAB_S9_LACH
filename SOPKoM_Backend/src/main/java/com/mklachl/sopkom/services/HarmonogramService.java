package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi harmonogramów
 */
public interface HarmonogramService {

    /**
     * Zapisuje nowy harmonogram
     * @param harmonogram encja harmonogram
     * @return zapisany harmonogram
     */
    Harmonogram saveHarmonogram(Harmonogram harmonogram);

    /**
     * Zapisuje nowy harmonogram na podstawie DTO
     * @param harmonogramDto DTO harmonogramu
     * @return zapisany harmonogram
     */
    Harmonogram saveHarmonogram(HarmonogramDto harmonogramDto);

    /**
     * Znajduje harmonogram po ID
     * @param id ID harmonogramu
     * @return Optional z harmonogramem
     */
    Optional<Harmonogram> findHarmonogramById(Short id);

    /**
     * Znajduje harmonogramy po nazwie
     * @param nazwa nazwa
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByNazwa(String nazwa);

    /**
     * Znajduje harmonogramy, które są aktywne w poniedziałki
     * @param pon poniedziałek
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByPon(boolean pon);

    /**
     * Znajduje harmonogramy, które są aktywne we wtorki
     * @param wto wtorek
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByWto(boolean wto);

    /**
     * Znajduje harmonogramy, które są aktywne w środy
     * @param sro środa
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramBySro(boolean sro);

    /**
     * Znajduje harmonogramy, które są aktywne w czwartki
     * @param czw czwartek
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByCzw(boolean czw);

    /**
     * Znajduje harmonogramy, które są aktywne w piątki
     * @param pia piątek
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByPia(boolean pia);

    /**
     * Znajduje harmonogramy, które są aktywne w soboty
     * @param sob sobota
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramBySob(boolean sob);

    /**
     * Znajduje harmonogramy, które są aktywne w niedziele
     * @param nie niedziela
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByNie(boolean nie);

    /**
     * Znajduje harmonogramy, które pasują do podanych dodatkowych informacji
     * @param dodatkoweInf dodatkowe informacje
     * @return lista harmonogramów
     */
    List<Harmonogram> findHarmonogramByDodatkoweInfLike(String dodatkoweInf);

}
