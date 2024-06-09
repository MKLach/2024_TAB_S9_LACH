package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.model.entity.Przystanek;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;

import java.util.List;

/**
 * Interfejs repository dla encji PrzystanekWlini
 */
public interface PrzystanekWliniRepository extends CrudRepository<PrzystanekWlini, Long> {

    /**
     * Znajduje wszystkie przystanki w linii dla danego przystanku
     * @param przystanek przystanek
     * @return Lista przystanków w linii
     */
    List<PrzystanekWlini> findAllByPrzystanek(Przystanek przystanek);

    /**
     * Znajduje wszystkie przystanki w linii dla danej linii
     * @param linia linia
     * @return Lista przystanków w linii
     */
    List<PrzystanekWlini> findAllByLinia(Linia linia);

    /**
     * Znajduje wszystkie przystanki w linii
     * @return Lista przystanków w linii
     */
    List<PrzystanekWlini> findAll();
}
