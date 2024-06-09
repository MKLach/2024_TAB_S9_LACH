package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.model.dto.PrzystanekDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi przystanków
 */
public interface PrzystanekService {

    /**
     * Zapisuje nowy przystanek
     * @param przystanek encja przystanku
     * @return zapisany przystanek
     */
    Przystanek savePrzystanek(Przystanek przystanek);

    /**
     * Zapisuje nowy przystanek na podstawie DTO
     * @param przystanekDto DTO przystanku
     * @return zapisany przystanek
     */
    Przystanek savePrzystanek(PrzystanekDto przystanekDto);

    /**
     * Znajduje przystanek po nazwie
     * @param nazwa nazwa przystanku
     * @return znaleziony przystanek
     */
    Przystanek findPrzystanekByNazwa(String nazwa);

    /**
     * Znajduje przystanki po kodzie pocztowym
     * @param kodPocztowy kod pocztowy
     * @return lista przystanków
     */
    List<Przystanek> findPrzystanekByKodPocztowy(String kodPocztowy);

    /**
     * Znajduje przystanki po mieście
     * @param miasto miasto
     * @return lista przystanków
     */
    List<Przystanek> findPrzystanekByMiasto(String miasto);

    /**
     * Znajduje przystanki po ulicy
     * @param ulica ulica
     * @return lista przystanków
     */
    List<Przystanek> findPrzystanekByUlica(String ulica);

    /**
     * Znajduje przystanki po szerokości geograficznej
     * @param szerokoscGeograficzna szerokość geograficzna
     * @return lista przystanków
     */
    List<Przystanek> findPrzystanekBySzerokoscGeograficzna(String szerokoscGeograficzna);

    /**
     * Znajduje przystanki po długości geograficznej
     * @param dlugoscGeograficzna długość geograficzna
     * @return lista przystanków
     */
    List<Przystanek> findPrzystanekByDlugoscGeograficzna(String dlugoscGeograficzna);

    /**
     * Znajduje przystanek po ID
     * @param id ID przystanku
     * @return Optional z przystankiem
     */
    Optional<Przystanek> findPrzystanekById(Long id);

    /**
     * Znajduje przystanki po przystanku odwrotnym
     * @param przystanekOdwrotny przystanek odwrotny
     * @return lista przystanków
     */
    List<Przystanek> findPrzystanekByPrzystanekOdwrotny(Przystanek przystanekOdwrotny);
}
