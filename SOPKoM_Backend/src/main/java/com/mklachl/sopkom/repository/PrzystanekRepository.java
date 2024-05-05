package com.mklachl.sopkom.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przystanek;

public interface PrzystanekRepository extends CrudRepository<Przystanek, Long> {

    List<Przystanek> findAllByPrzystanekOdwrotny(Przystanek przystanekOdwrotny);

    Przystanek findByNazwa(String nazwa);
    
    List<Przystanek> findAllByNazwaLike(String nazwa);

    List<Przystanek> findAllByKodPocztowy(String kodPocztowy);

    List<Przystanek> findAllByMiasto(String miasto);

    List<Przystanek> findAllByUlica(String ulica);
    
    List<Przystanek> findAllByUlicaLike(String ulica);  

    List<Przystanek> findAllBySzerokoscGeograficzna(String szerokoscGeograficzna);

    List<Przystanek> findAllByDlugoscGeograficzna(String dlugoscGeograficzna);
    
    List<Przystanek> findAll();    

}
