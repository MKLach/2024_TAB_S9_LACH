package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji Incydent
 */
public interface IncydentRepository extends CrudRepository<Incydent, Long> {

    /**
     * Znajduje wszystkie incydenty o określonym typie
     * @param typ typ incydentu
     * @return Lista incydentów
     */
    List<Incydent> findAllByTyp(String typ);

    /**
     * Znajduje wszystkie incydenty przed podaną datą
     * @param date data
     * @return Lista incydentów
     */
    List<Incydent> findAllByDateBefore(Date date);

    /**
     * Znajduje wszystkie incydenty po podanej dacie
     * @param date data
     * @return Lista incydentów
     */
    List<Incydent> findAllByDateAfter(Date date);

    /**
     * Znajduje wszystkie incydenty pomiędzy dwiema datami
     * @param date1 data początkowa
     * @param date2 data końcowa
     * @return Lista incydentów
     */
    List<Incydent> findAllByDateBetween(Date date1, Date date2);

    /**
     * Znajduje wszystkie incydenty w danym dniu
     * @param date data
     * @return Lista incydentów
     */
    List<Incydent> findAllByDate(Date date);

    /**
     * Znajduje wszystkie incydenty związane z danym autobusem
     * @param autobus autobus
     * @return Lista incydentów
     */
    List<Incydent> findAllByAutobus(Autobus autobus);

    /**
     * Znajduje wszystkie incydenty związane z danym kierowcą
     * @param kierowca kierowca
     * @return Lista incydentów
     */
    List<Incydent> findAllByKierowca(Kierowca kierowca);

    /**
     * Znajduje wszystkie incydenty związane z danym przejazdem
     * @param przejazd przejazd
     * @return Lista incydentów
     */
    List<Incydent> findAllByPrzejazd(Przejazd przejazd);

    /**
     * Znajduje wszystkie incydenty z podanymi dodatkowymi informacjami
     * @param dodatkoweInformacje dodatkowe informacje
     * @return Lista incydentów
     */
    List<Incydent> findAllByDodatkoweInformacje(String dodatkoweInformacje);

    /**
     * Znajduje wszystkie incydenty z kosztami mniejszymi niż podane
     * @param koszty koszty
     * @return Lista incydentów
     */
    List<Incydent> findAllByKosztyLessThan(float koszty);

    /**
     * Znajduje wszystkie incydenty z kosztami większymi niż podane
     * @param koszty koszty
     * @return Lista incydentów
     */
    List<Incydent> findAllByKosztyGreaterThan(float koszty);

    /**
     * Znajduje wszystkie incydenty z dokładnymi kosztami
     * @param koszty koszty
     * @return Lista incydentów
     */
    List<Incydent> findAllByKoszty(float koszty);

    /**
     * Znajduje wszystkie incydenty
     * @return Lista incydentów
     */
    List<Incydent> findAll();
}
