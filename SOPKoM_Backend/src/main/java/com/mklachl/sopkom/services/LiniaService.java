package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.exceptions.LiniaNotFoundException;
import com.mklachl.sopkom.exceptions.PrzystanekNotFoundException;
import com.mklachl.sopkom.model.dto.LiniaDtoOutput;
import com.mklachl.sopkom.model.dto.LiniaDtoInput;

import java.util.Optional;

public interface LiniaService {
    Linia saveLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException;
    
    Linia patchLinia(LiniaDtoInput liniaDto) throws PrzystanekNotFoundException, LiniaNotFoundException;
    
    
    void deleteLinia(Long id);

    Optional<Linia> findLiniaById(Long id);

    Linia findLiniaByNumer(String numer);
}
