package com.mklachl.sopkom.repository;

import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Autobus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji Autobus
 */
public interface AutobusRepository extends CrudRepository<Autobus, Long> {

    /**
     * Znajduje autobus po numerze rejestracyjnym
     * @param numerRejestracyjny numer rejestracyjny
     * @return Autobus
     */
    Autobus findByNumerRejestracyjny(String numerRejestracyjny);

    /**
     * Znajduje autobus z przeglądem ważnym przed określoną datą
     * @param date data
     * @return Autobus
     */
    Autobus findByPrzegladWaznyDoBefore(Date date);

    /**
     * Znajduje autobus z przeglądem ważnym po określonej dacie
     * @param date data
     * @return Autobus
     */
    Autobus findByPrzegladWaznyDoAfter(Date date);

    /**
     * Znajduje wszystkie autobusy o określonym statusie
     * @param status status
     * @return Lista autobusów
     */
    List<Autobus> findAllByStatus(String status);

    /**
     * Znajduje wszystkie autobusy z przebiegiem większym niż podany
     * @param przebieg przebieg
     * @return Lista autobusów
     */
    List<Autobus> findAllByPrzebiegGreaterThan(float przebieg);

    /**
     * Znajduje wszystkie autobusy z przebiegiem mniejszym niż podany
     * @param przebieg przebieg
     * @return Lista autobusów
     */
    List<Autobus> findAllByPrzebiegLessThan(float przebieg);

    /**
     * Znajduje wszystkie autobusy z dokładnym przebiegiem
     * @param przebieg przebieg
     * @return Lista autobusów
     */
    List<Autobus> findAllByPrzebieg(float przebieg);

    /**
     * Znajduje wszystkie autobusy o statusie pasującym do wzorca
     * @param status status
     * @return Lista autobusów
     */
    List<Autobus> findByStatusLike(String status);

    /**
     * Znajduje wszystkie autobusy o numerze rejestracyjnym pasującym do wzorca
     * @param numerRejestracyjny numer rejestracyjny
     * @return Lista autobusów
     */
    List<Autobus> findAllByNumerRejestracyjnyLike(String numerRejestracyjny);

    /**
     * Znajduje wszystkie autobusy
     * @return Lista autobusów
     */
    List<Autobus> findAll();

    /**
     * Znajduje wszystkie dostępne autobusy w podanym przedziale czasowym
     * @param startTime czas rozpoczęcia
     * @param endTime czas zakończenia
     * @return Lista autobusów
     */
    @Query(value = "SELECT * FROM autobusy a WHERE a.autbous_id NOT IN (SELECT p.autobus_id FROM przejazdy p WHERE p.data_startu < :endTime AND p.data_konca > :startTime)", nativeQuery = true)
    List<Autobus> findAllByAvailable(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
