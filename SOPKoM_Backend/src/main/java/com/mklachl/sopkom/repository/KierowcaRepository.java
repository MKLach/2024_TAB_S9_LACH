package com.mklachl.sopkom.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji Kierowca
 */
public interface KierowcaRepository extends CrudRepository<Kierowca, Long> {

    /**
     * Znajduje kierowcę po numerze PESEL
     * @param pesel numer PESEL
     * @return Kierowca
     */
    Kierowca findByPesel(String pesel);
    
    /**
     * Znajduje wszystkich kierowców, których numer PESEL pasuje do wzorca
     * @param pesel numer PESEL
     * @return Lista kierowców
     */
    List<Kierowca> findAllByPeselLike(String pesel);

    /**
     * Znajduje kierowcę powiązanego z danym użytkownikiem
     * @param user użytkownik
     * @return Kierowca
     */
    Kierowca findByUser(User user);

    /**
     * Znajduje wszystkich kierowców o podanym imieniu i nazwisku
     * @param imie imię
     * @param nazwisko nazwisko
     * @return Lista kierowców
     */
    List<Kierowca> findAllByImieAndNazwisko(String imie, String nazwisko);
    
    /**
     * Znajduje wszystkich kierowców, których imię i nazwisko pasuje do wzorca
     * @param imie imię
     * @param nazwisko nazwisko
     * @return Lista kierowców
     */
    List<Kierowca> findAllByImieAndNazwiskoLike(String imie, String nazwisko);

    /**
     * Znajduje wszystkich kierowców, których prawo jazdy jest ważne do podanej daty
     * @param date data
     * @return Lista kierowców
     */
    List<Kierowca> findAllByPrawoJazdyWazneDoBefore(Date date);

    /**
     * Znajduje wszystkich kierowców, których prawo jazdy jest ważne po podanej dacie
     * @param date data
     * @return Lista kierowców
     */
    List<Kierowca> findAllByPrawoJazdyWazneDoAfter(Date date);
    
    /**
     * Znajduje wszystkich kierowców
     * @return Lista kierowców
     */
    List<Kierowca> findAll();

    /**
     * Znajduje wszystkich kierowców, których nazwisko pasuje do wzorca
     * @param nazwisko nazwisko
     * @return Lista kierowców
     */
    List<Kierowca> findByNazwiskoLike(String nazwisko);

    /**
     * Znajduje wszystkich dostępnych kierowców w podanym przedziale czasowym
     * @param startTime czas rozpoczęcia
     * @param endTime czas zakończenia
     * @return Lista kierowców
     */
    @Query(value = "SELECT * FROM kierowcy k WHERE k.kierowca_id NOT IN  (SELECT p.kierowca_id FROM przejazdy p WHERE p.data_startu < :endTime AND p.data_konca > :startTime)", nativeQuery = true)
    List<Kierowca> findAllByAvailability(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
