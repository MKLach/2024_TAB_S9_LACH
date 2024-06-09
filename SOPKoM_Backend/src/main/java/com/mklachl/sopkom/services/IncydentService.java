package com.mklachl.sopkom.services;

import com.mklachl.sopkom.exceptions.IncydentException;
import com.mklachl.sopkom.model.dto.IncydentDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi incydentów
 */
public interface IncydentService {

    /**
     * Zapisuje nowy incydent
     * @param incydent encja incydent
     * @return zapisany incydent
     */
    Incydent saveIncydent(Incydent incydent);

    /**
     * Zapisuje nowy incydent na podstawie DTO
     * @param incydentDto DTO incydentu
     * @return zapisany incydent
     * @throws IncydentException wyjątek incydentu
     */
    Incydent saveIncydent(IncydentDto incydentDto) throws IncydentException;

    /**
     * Aktualizuje istniejący incydent na podstawie ID
     * @param id ID incydentu
     * @param updatedIncydentDto zaktualizowany DTO incydentu
     * @return zaktualizowany incydent
     * @throws IncydentException wyjątek incydentu
     */
    Incydent updateIncydent(long id, IncydentDto updatedIncydentDto) throws IncydentException;

    /**
     * Znajduje incydent po ID
     * @param id ID incydentu
     * @return Optional z incydentem
     */
    Optional<Incydent> findIncydentById(long id);

    /**
     * Znajduje incydenty po typie
     * @param typ typ
     * @return lista incydentów
     */
    List<Incydent> findIncydentByTyp(String typ);

    /**
     * Znajduje incydenty przed podaną datą
     * @param date data
     * @return lista incydentów
     */
    List<Incydent> findIncydentByDateBefore(Date date);

    /**
     * Znajduje incydenty po podanej dacie
     * @param date data
     * @return lista incydentów
     */
    List<Incydent> findIncydentByDateAfter(Date date);

    /**
     * Znajduje incydenty w przedziale między dwiema datami
     * @param date1 pierwsza data
     * @param date2 druga data
     * @return lista incydentów
     */
    List<Incydent> findIncydentByDateBetween(Date date1, Date date2);

    /**
     * Znajduje incydenty po dokładnej dacie
     * @param date data
     * @return lista incydentów
     */
    List<Incydent> findIncydentByDate(Date date);

    /**
     * Znajduje incydenty dla podanego autobusu
     * @param autobus autobus
     * @return lista incydentów
     */
    List<Incydent> findIncydentByAutobus(Autobus autobus);

    /**
     * Znajduje incydenty dla podanego kierowcy
     * @param kierowca kierowca
     * @return lista incydentów
     */
    List<Incydent> findIncydentByKierowca(Kierowca kierowca);

    /**
     * Znajduje incydenty dla podanego przejazdu
     * @param przejazd przejazd
     * @return lista incydentów
     */
    List<Incydent> findIncydentByPrzejazd(Przejazd przejazd);

    /**
     * Znajduje incydenty po dodatkowych informacjach
     * @param dodatkoweInformacje dodatkowe informacje
     * @return lista incydentów
     */
    List<Incydent> findIncydentByDodatkoweInformacje(String dodatkoweInformacje);

    /**
     * Znajduje incydenty, których koszty są mniejsze niż podane
     * @param koszty koszty
     * @return lista incydentów
     */
    List<Incydent> findIncydentByKosztyLessThan(float koszty);

    /**
     * Znajduje incydenty, których koszty są większe niż podane
     * @param koszty koszty
     * @return lista incydentów
     */
    List<Incydent> findIncydentByKosztyGreaterThan(float koszty);

    /**
     * Znajduje incydenty po dokładnych kosztach
     * @param koszty koszty
     * @return lista incydentów
     */
    List<Incydent> findIncydentByKoszty(float koszty);
}
