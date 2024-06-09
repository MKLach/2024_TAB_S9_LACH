package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Przejazd;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji Przejazd
 */
public interface PrzejazdRepository extends CrudRepository<Przejazd, Long> {

    /**
     * Znajduje wszystkie przejazdy dla danego kierowcy
     * @param kierowca kierowca
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByKierowca(Kierowca kierowca);

    /**
     * Znajduje wszystkie przejazdy dla danego autobusu
     * @param autobus autobus
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByAutobus(Autobus autobus);

    /**
     * Znajduje wszystkie przejazdy dla danego kursu
     * @param kurs kurs
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByKurs(Kurs kurs);

    /**
     * Znajduje wszystkie przejazdy o określonym spalaniu
     * @param spalanie spalanie
     * @return Lista przejazdów
     */
    List<Przejazd> findAllBySpalanie(float spalanie);

    /**
     * Znajduje wszystkie przejazdy o spalaniu mniejszym niż podane
     * @param spalanie spalanie
     * @return Lista przejazdów
     */
    List<Przejazd> findAllBySpalanieLessThan(float spalanie);

    /**
     * Znajduje wszystkie przejazdy o spalaniu większym niż podane
     * @param spalanie spalanie
     * @return Lista przejazdów
     */
    List<Przejazd> findAllBySpalanieGreaterThan(float spalanie);

    /**
     * Znajduje wszystkie przejazdy o określonej cenie za litr
     * @param cenaZaLitr cena za litr
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByCenaZaLitr(float cenaZaLitr);

    /**
     * Znajduje wszystkie przejazdy o cenie za litr mniejszej niż podana
     * @param cenaZaLitr cena za litr
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByCenaZaLitrLessThan(float cenaZaLitr);

    /**
     * Znajduje wszystkie przejazdy o cenie za litr większej niż podana
     * @param cenaZaLitr cena za litr
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByCenaZaLitrGreaterThan(float cenaZaLitr);

    /**
     * Znajduje wszystkie przejazdy o określonej długości trasy
     * @param dlugoscTrasy długość trasy
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDlugoscTrasy(float dlugoscTrasy);

    /**
     * Znajduje wszystkie przejazdy o długości trasy mniejszej niż podana
     * @param dlugoscTrasy długość trasy
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDlugoscTrasyLessThan(float dlugoscTrasy);

    /**
     * Znajduje wszystkie przejazdy o długości trasy większej niż podana
     * @param dlugoscTrasy długość trasy
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDlugoscTrasyGreaterThan(float dlugoscTrasy);

    /**
     * Znajduje wszystkie przejazdy po dacie rozpoczęcia
     * @param dataStartu data rozpoczęcia
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDataStartu(Date dataStartu);

    /**
     * Znajduje wszystkie przejazdy po dacie rozpoczęcia wcześniejszej niż podana
     * @param dataStartu data rozpoczęcia
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDataStartuBefore(Date dataStartu);

    /**
     * Znajduje wszystkie przejazdy po dacie rozpoczęcia późniejszej niż podana
     * @param dataStartu data rozpoczęcia
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDataStartuAfter(Date dataStartu);

    /**
     * Znajduje wszystkie przejazdy po dacie zakończenia
     * @param dataKonca data zakończenia
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDataKonca(Date dataKonca);

    /**
     * Znajduje wszystkie przejazdy po dacie zakończenia wcześniejszej niż podana
     * @param dataKonca data zakończenia
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDataKoncaBefore(Date dataKonca);

    /**
     * Znajduje wszystkie przejazdy po dacie zakończenia późniejszej niż podana
     * @param dataKonca data zakończenia
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByDataKoncaAfter(Date dataKonca);

    /**
     * Znajduje wszystkie przejazdy po dokładnej dacie
     * @param data data
     * @return Lista przejazdów
     */
    List<Przejazd> findAllByData(Date data);

    /**
     * Znajduje wszystkie przejazdy dla danego kursu i daty przejazdu
     * @param kurs kurs
     * @param dataPrzejazdu data przejazdu
     * @return Lista przejazdów
     */
    @Query(value = "SELECT * FROM przejazdy p WHERE p.kurs_id = :kursId AND (p.data_startu = :dataPrzejazdu or p.data_konca = :dataPrzejazdu)", nativeQuery = true)
    List<Przejazd> findAllByKursAndData(@Param("kurs") Kurs kurs, @Param("dataPrzejazdu") Date dataPrzejazdu);

    /**
     * Znajduje wszystkie przejazdy dla danej daty przejazdu
     * @param kurs kurs
     * @param dataPrzejazdu data przejazdu
     * @return Lista przejazdów
     */
    @Query(value = "SELECT * FROM przejazdy p WHERE p.kurs_id = :kursId AND (p.data_startu = :dataPrzejazdu or p.data_konca = :dataPrzejazdu)", nativeQuery = true)
    List<Przejazd> findAllByData(@Param("kurs") Kurs kurs, @Param("dataPrzejazdu") Date dataPrzejazdu);

    /**
     * Znajduje wszystkie przejazdy
     * @return Lista przejazdów
     */
    List<Przejazd> findAll();
}
