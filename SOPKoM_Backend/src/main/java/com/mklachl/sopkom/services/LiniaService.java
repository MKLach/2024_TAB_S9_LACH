package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.linia.LiniaDtoInput;
import com.mklachl.sopkom.model.dto.linia.LiniaDtoOutput;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.exceptions.LiniaNotFoundException;
import com.mklachl.sopkom.exceptions.PrzystanekNotFoundException;

import java.util.Optional;

public interface LiniaService {
    Linia saveLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException;
    
    Linia patchLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException, LiniaNotFoundException;
    
    
    void deleteLinia(Long id);

    Optional<Linia> findLiniaById(Long id);

    Linia findLiniaByNumer(String numer);
}
