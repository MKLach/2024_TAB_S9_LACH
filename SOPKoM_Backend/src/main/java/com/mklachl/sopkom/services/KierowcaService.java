package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi kierowców
 */
public interface KierowcaService {

    /**
     * Zapisuje nowego kierowcę
     * @param kierowca encja kierowcy
     * @return zapisany kierowca
     */
    Kierowca saveKierowca(Kierowca kierowca);

    /**
     * Zapisuje nowego kierowcę na podstawie DTO
     * @param kierowcaDto DTO kierowcy
     * @return zapisany kierowca
     */
    Kierowca saveKierowca(KierowcaDto kierowcaDto);

    /**
     * Znajduje kierowcę po numerze PESEL
     * @param pesel numer PESEL
     * @return znaleziony kierowca
     */
    Kierowca findKierowcaByPesel(String pesel);

    /**
     * Znajduje kierowcę powiązanego z użytkownikiem
     * @param user użytkownik
     * @return znaleziony kierowca
     */
    Kierowca findKierowcaByUser(User user);

    /**
     * Znajduje kierowców po imieniu i nazwisku
     * @param imie imię
     * @param nazwisko nazwisko
     * @return lista kierowców
     */
    List<Kierowca> findKierowcaByImieAndNazwisko(String imie, String nazwisko);

    /**
     * Znajduje kierowców, których prawo jazdy jest ważne do podanej daty
     * @param date data
     * @return lista kierowców
     */
    List<Kierowca> findKierowcaByPrawoJazdyWazneDoBefore(Date date);

    /**
     * Znajduje kierowców, których prawo jazdy jest ważne po podanej dacie
     * @param date data
     * @return lista kierowców
     */
    List<Kierowca> findKierowcaByPrawoJazdyWazneDoAfter(Date date);

    /**
     * Znajduje kierowcę po ID
     * @param id ID kierowcy
     * @return Optional z kierowcą
     */
    Optional<Kierowca> findKierowcaById(Long id);

    /**
     * Znajduje dostępnych kierowców w podanym przedziale czasowym
     * @param startTime czas rozpoczęcia
     * @param endTime czas zakończenia
     * @return lista dostępnych kierowców
     */
    List<Kierowca> findKierowcaByAvailability(Date startTime, Date endTime);

}
