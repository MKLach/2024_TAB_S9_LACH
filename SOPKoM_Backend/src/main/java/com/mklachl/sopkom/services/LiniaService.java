package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.model.dto.LiniaDto;

import java.util.Optional;

public interface LiniaService {
    LiniaDto saveLinia(LiniaDto liniaDto);
    void deleteLinia(Long id);

    Optional<Linia> findLiniaById(Long id);

    Linia findLiniaByNumer(String numer);
}
