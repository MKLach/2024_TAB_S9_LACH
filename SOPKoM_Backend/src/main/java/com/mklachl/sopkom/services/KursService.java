package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.kurs.AbstractKursDto;
import com.mklachl.sopkom.model.dto.kurs.InputKursDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Linia;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi kursów
 */
public interface KursService {

    /**
     * Zapisuje nowy kurs
     * @param kurs encja kurs
     * @return zapisany kurs
     */
    Kurs saveKurs(Kurs kurs);

    /**
     * Zapisuje nowy kurs na podstawie DTO
     * @param kursDto DTO kursu
     * @return zapisany kurs
     * @throws Exception wyjątek ogólny
     */
    Kurs saveKurs(InputKursDto kursDto) throws Exception;
    
    /**
     * Aktualizuje istniejący kurs na podstawie DTO
     * @param kursDto DTO kursu
     * @return zaktualizowany kurs
     * @throws Exception wyjątek ogólny
     */
    Kurs updateKurs(InputKursDto kursDto) throws Exception;

    /**
     * Znajduje kursy po linii
     * @param linia linia
     * @return lista kursów
     */
    List<Kurs> findKursByLinia(Linia linia);

    /**
     * Znajduje kursy po harmonogramie
     * @param harmonogram harmonogram
     * @return lista kursów
     */
    List<Kurs> findKursByHarmonogram(Harmonogram harmonogram);

    /**
     * Znajduje kursy po kierunku
     * @param kierunek kierunek
     * @return lista kursów
     */
    List<Kurs> findKursByKierunek(Short kierunek);

    /**
     * Znajduje kursy po typie autobusu
     * @param typAutobusu typ autobusu
     * @return lista kursów
     */
    List<Kurs> findKursByTypAutobusu(Short typAutobusu);
  
}
