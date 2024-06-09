package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Bilet;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdBilet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfejs repository dla encji PrzejazdBilet
 */
public interface PrzejazdBiletRepository extends CrudRepository<PrzejazdBilet, Long> {

    /**
     * Znajduje wszystkie bilety w przejeździe dla danego przejazdu
     * @param przejazd przejazd
     * @return Lista biletów w przejeździe
     */
    List<PrzejazdBilet> findAllByPrzejazd(Przejazd przejazd);

    /**
     * Znajduje wszystkie bilety w przejeździe dla danego biletu
     * @param bilet bilet
     * @return Lista biletów w przejeździe
     */
    List<PrzejazdBilet> findAllByBilet(Bilet bilet);

    /**
     * Znajduje wszystkie bilety w przejeździe o cenie większej niż podana
     * @param cenaBiletu cena biletu
     * @return Lista biletów w przejeździe
     */
    List<PrzejazdBilet> findAllByCenaBiletuGreaterThan(float cenaBiletu);

    /**
     * Znajduje wszystkie bilety w przejeździe o cenie mniejszej niż podana
     * @param cenaBiletu cena biletu
     * @return Lista biletów w przejeździe
     */
    List<PrzejazdBilet> findAllByCenaBiletuLessThan(float cenaBiletu);

    /**
     * Znajduje wszystkie bilety w przejeździe o dokładnej cenie
     * @param cenaBiletu cena biletu
     * @return Lista biletów w przejeździe
     */
    List<PrzejazdBilet> findAllByCenaBiletu(float cenaBiletu);

    /**
     * Znajduje wszystkie bilety w przejeździe
     * @return Lista biletów w przejeździe
     */
    List<PrzejazdBilet> findAll();
}
