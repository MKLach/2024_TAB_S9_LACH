package com.mklachl.sopkom.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mklachl.sopkom.model.entity.Przystanek;

/**
 * Interfejs repository dla encji Przystanek
 */
public interface PrzystanekRepository extends CrudRepository<Przystanek, Long> {

    /**
     * Znajduje wszystkie przystanki dla danego przystanku odwrotnego
     * @param przystanekOdwrotny przystanek odwrotny
     * @return Lista przystanków
     */
    List<Przystanek> findAllByPrzystanekOdwrotny(Przystanek przystanekOdwrotny);

    /**
     * Znajduje przystanek po nazwie
     * @param nazwa nazwa
     * @return Przystanek
     */
    Przystanek findByNazwa(String nazwa);

    /**
     * Znajduje wszystkie przystanki, których nazwa pasuje do podanej frazy
     * @param nazwa nazwa
     * @return Lista przystanków
     */
    List<Przystanek> findAllByNazwaLike(String nazwa);

    /**
     * Znajduje wszystkie przystanki po kodzie pocztowym
     * @param kodPocztowy kod pocztowy
     * @return Lista przystanków
     */
    List<Przystanek> findAllByKodPocztowy(String kodPocztowy);

    /**
     * Znajduje wszystkie przystanki po mieście
     * @param miasto miasto
     * @return Lista przystanków
     */
    List<Przystanek> findAllByMiasto(String miasto);

    /**
     * Znajduje wszystkie przystanki po ulicy
     * @param ulica ulica
     * @return Lista przystanków
     */
    List<Przystanek> findAllByUlica(String ulica);

    /**
     * Znajduje wszystkie przystanki, których ulica pasuje do podanej frazy
     * @param ulica ulica
     * @return Lista przystanków
     */
    List<Przystanek> findAllByUlicaLike(String ulica);

    /**
     * Znajduje wszystkie przystanki po szerokości geograficznej
     * @param szerokoscGeograficzna szerokość geograficzna
     * @return Lista przystanków
     */
    List<Przystanek> findAllBySzerokoscGeograficzna(String szerokoscGeograficzna);

    /**
     * Znajduje wszystkie przystanki po długości geograficznej
     * @param dlugoscGeograficzna długość geograficzna
     * @return Lista przystanków
     */
    List<Przystanek> findAllByDlugoscGeograficzna(String dlugoscGeograficzna);

    /**
     * Znajduje wszystkie przystanki
     * @return Lista przystanków
     */
    List<Przystanek> findAll();
}
