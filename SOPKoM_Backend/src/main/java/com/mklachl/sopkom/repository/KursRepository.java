package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Linia;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Kurs;

import java.util.List;

/**
 * Interfejs repository dla encji Kurs
 */
public interface KursRepository extends CrudRepository<Kurs, Long> {

    /**
     * Znajduje wszystkie kursy dla danej linii
     * @param linia linia
     * @return Lista kursów
     */
    List<Kurs> findAllByLinia(Linia linia);

    /**
     * Znajduje wszystkie kursy dla danego harmonogramu
     * @param harmonogram harmonogram
     * @return Lista kursów
     */
    List<Kurs> findAllByHarmonogram(Harmonogram harmonogram);

    /**
     * Znajduje wszystkie kursy o określonym kierunku
     * @param kierunek kierunek
     * @return Lista kursów
     */
    List<Kurs> findAllByKierunek(Short kierunek);

    /**
     * Znajduje wszystkie kursy o określonym typie autobusu
     * @param typAutobusu typ autobusu
     * @return Lista kursów
     */
    List<Kurs> findAllByTypAutobusu(Short typAutobusu);

    /**
     * Znajduje wszystkie kursy
     * @return Lista kursów
     */
    List<Kurs> findAll();
}
