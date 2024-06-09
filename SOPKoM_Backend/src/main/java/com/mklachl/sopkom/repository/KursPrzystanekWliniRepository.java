package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji KursPrzystanekWlini
 */
public interface KursPrzystanekWliniRepository extends CrudRepository<KursPrzystanekWlini, Long> {

    /**
     * Znajduje wszystkie przystanki w kursie dla danego kursu
     * @param kurs kurs
     * @return Lista przystanków w kursie
     */
    List<KursPrzystanekWlini> findAllByKurs(Kurs kurs);

    /**
     * Znajduje wszystkie kursy przystanku w danym przystanku w linii
     * @param przystanekWlini przystanek w linii
     * @return Lista kursów przystanku w linii
     */
    List<KursPrzystanekWlini> findAllByPrzystanekWlini(PrzystanekWlini przystanekWlini);

    /**
     * Znajduje wszystkie kursy przystanku po dacie późniejszej niż podana
     * @param date data
     * @return Lista kursów przystanku
     */
    List<KursPrzystanekWlini> findAllByGodzinnaAfter(Date date);

    /**
     * Znajduje wszystkie kursy przystanku po dacie wcześniejszej niż podana
     * @param date data
     * @return Lista kursów przystanku
     */
    List<KursPrzystanekWlini> findAllByGodzinnaBefore(Date date);

    /**
     * Znajduje wszystkie kursy przystanku po dokładnej dacie
     * @param date data
     * @return Lista kursów przystanku
     */
    List<KursPrzystanekWlini> findAllByGodzinna(Date date);

    /**
     * Znajduje wszystkie kursy przystanku
     * @return Lista kursów przystanku
     */
    List<KursPrzystanekWlini> findAll();
}
