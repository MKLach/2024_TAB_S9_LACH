package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.model.dto.LiniaDto;
import com.mklachl.sopkom.repository.LiniaRepository;
import com.mklachl.sopkom.services.LiniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LiniaServiceImpl implements LiniaService {
    @Autowired
    private LiniaRepository liniaRepository;

    @Override
    public LiniaDto saveLinia(LiniaDto liniaDto) {
        Linia linia = mapToEntity(liniaDto);
        linia = liniaRepository.save(linia);
        return mapToDto(linia);
    }

    @Override
    public void deleteLinia(Long id) {
        liniaRepository.deleteById(id);
    }

    @Override
    public Optional<Linia> findLiniaById(Long id) {
        return liniaRepository.findById(id);
    }

    @Override
    public Linia findLiniaByNumer(String numer) {
        return liniaRepository.findByNumer(numer);
    }

    private LiniaDto mapToDto(Linia linia) {
        LiniaDto dto = new LiniaDto();
        dto.setId(linia.getLiniaId());
        dto.setNumer(linia.getNumer());
        return dto;
    }

    private Linia mapToEntity(LiniaDto dto) {
        Linia linia = new Linia();
        linia.setLiniaId(dto.getId());
        linia.setNumer(dto.getNumer());
        return linia;
    }
}

