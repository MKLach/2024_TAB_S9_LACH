package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Przystanek;
import com.mklachl.sopkom.model.dto.PrzystanekDto;

import java.util.List;
import java.util.Optional;

public interface PrzystanekService {
    void savePrzystanek(Przystanek przystanekDto);

    void savePrzystanek(PrzystanekDto przystanekDto);

    Przystanek findPrzystanekByNazwa(String nazwa);

    List<Przystanek> findPrzystanekByKodPocztowy(String kodPocztowy);

    List<Przystanek> findPrzystanekByMiasto(String miasto);

    List<Przystanek> findPrzystanekByUlica(String ulica);

    List<Przystanek> findPrzystanekBySzerokoscGeograficzna(String szerokoscGeograficzna);

    List<Przystanek> findPrzystanekByDlugoscGeograficzna(String dlugoscGeograficzna);

    Optional<Przystanek> findPrzystanekById(Long id);

    List<Przystanek> findPrzystanekByPrzystanekOdwrotny(Przystanek przystanekOdwrotny);
}
