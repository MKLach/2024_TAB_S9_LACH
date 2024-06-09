package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.linia.LiniaDtoInput;
import com.mklachl.sopkom.model.dto.linia.LiniaDtoOutput;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.exceptions.LiniaNotFoundException;
import com.mklachl.sopkom.exceptions.PrzystanekNotFoundException;

import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi linii
 */
public interface LiniaService {

    /**
     * Zapisuje nową linię na podstawie DTO
     * @param liniaDto DTO linii
     * @return zapisana linia
     * @throws PrzystanekNotFoundException wyjątek braku przystanku
     */
    Linia saveLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException;
    
    /**
     * Aktualizuje istniejącą linię na podstawie DTO
     * @param liniaDto DTO linii
     * @return zaktualizowana linia
     * @throws PrzystanekNotFoundException wyjątek braku przystanku
     * @throws LiniaNotFoundException wyjątek braku linii
     */
    Linia patchLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException, LiniaNotFoundException;
    
    /**
     * Usuwa linię na podstawie ID
     * @param id ID linii
     */
    void deleteLinia(Long id);

    /**
     * Znajduje linię po ID
     * @param id ID linii
     * @return Optional z linią
     */
    Optional<Linia> findLiniaById(Long id);

    /**
     * Znajduje linię po numerze
     * @param numer numer linii
     * @return znaleziona linia
     */
    Linia findLiniaByNumer(String numer);
}
