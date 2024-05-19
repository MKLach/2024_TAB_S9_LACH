package com.mklachl.sopkom.repository;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Linia;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Kurs;

import java.util.List;

public interface KursRepository extends CrudRepository<Kurs, Long>{

    List<Kurs> findAllByLinia(Linia linia);

    List<Kurs> findAllByHarmonogram(Harmonogram harmonogram);

    List<Kurs> findAllByKierunek(Short kierunek);

    List<Kurs> findAllByTypAutobusu(Short typAutobusu);

    List<Kurs> findAll();
}
