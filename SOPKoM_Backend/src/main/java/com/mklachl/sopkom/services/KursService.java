package com.mklachl.sopkom.services;
import com.mklachl.sopkom.model.dto.kurs.AbstractKursDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Linia;

import java.util.List;
import java.util.Optional;
public interface KursService {

    Kurs saveKurs(Kurs kurs);

    Kurs saveKurs(KursDto kursDto);

    List<Kurs> findKursByLinia(Linia linia);

    List<Kurs> findKursByHarmonogram(Harmonogram harmonogram);

    List<Kurs> findKursByKierunek(Short kierunek);

    List<Kurs> findKursByTypAutobusu(Short typAutobusu);
}
