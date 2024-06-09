package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.dto.AutobusDto;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi autobusów
 */
public interface AutobusService {

    /**
     * Zapisuje nowy autobus
     * @param autobus encja autobus
     * @return zapisany autobus
     */
    Autobus saveAutobus(Autobus autobus);

    /**
     * Zapisuje nowy autobus na podstawie DTO
     * @param autobusDto DTO autobusu
     * @return zapisany autobus
     */
    Autobus saveAutobus(AutobusDto autobusDto);

    /**
     * Znajduje autobus po numerze rejestracyjnym
     * @param numerRejestracyjny numer rejestracyjny
     * @return znaleziony autobus
     */
    Autobus findAutobusByNumerRejestracyjny(String numerRejestracyjny);

    /**
     * Znajduje autobus po ID
     * @param id ID autobusu
     * @return Optional z autobusem
     */
    Optional<Autobus> findAutobusById(long id);

    /**
     * Znajduje autobus, którego przegląd jest ważny do podanej daty
     * @param date data
     * @return znaleziony autobus
     */
    Autobus findAutobusByPrzegladWaznyDoBefore(Date date);

    /**
     * Znajduje autobus, którego przegląd jest ważny po podanej dacie
     * @param date data
     * @return znaleziony autobus
     */
    Autobus findAutobusByPrzegladWaznyDoAfter(Date date);

    /**
     * Znajduje autobusy po statusie
     * @param status status
     * @return lista autobusów
     */
    List<Autobus> findAutobusByStatus(String status);

    /**
     * Znajduje autobusy, których przebieg jest większy niż podany
     * @param przebieg przebieg
     * @return lista autobusów
     */
    List<Autobus> findAutobusByPrzebiegGreaterThan(float przebieg);

    /**
     * Znajduje autobusy, których przebieg jest mniejszy niż podany
     * @param przebieg przebieg
     * @return lista autobusów
     */
    List<Autobus> findAutobusByPrzebiegLessThan(float przebieg);

    /**
     * Znajduje autobusy po dokładnym przebiegu
     * @param przebieg przebieg
     * @return lista autobusów
     */
    List<Autobus> findAutobusByPrzebieg(float przebieg);

    /**
     * Znajduje dostępne autobusy w podanym przedziale czasowym
     * @param startTime czas rozpoczęcia
     * @param endTime czas zakończenia
     * @return lista dostępnych autobusów
     */
    List<Autobus> findAutobusByAvailable(Date startTime, Date endTime);

}
