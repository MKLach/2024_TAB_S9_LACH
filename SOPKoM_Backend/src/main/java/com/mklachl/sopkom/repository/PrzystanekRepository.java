package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Przystanek;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrzystanekRepository extends CrudRepository<Przystanek, Long> {

    List<Przystanek> findByPrzystanekOdwrotny(Przystanek przystanekOdwrotny);

    Przystanek findByNazwa(String nazwa);

    List<Przystanek> findByKodPocztowy(String kodPocztowy);

    List<Przystanek> findByMiasto(String miasto);

    List<Przystanek> findByUlica(String ulica);

    List<Przystanek> findBySzerokoscGeograficzna(String szerokoscGeograficzna);

    List<Przystanek> findByDlugoscGeograficzna(String dlugoscGeograficzna);

}
