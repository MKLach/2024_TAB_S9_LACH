package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoInput;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoUpdate;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Przejazd;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu dla obsługi przejazdów
 */
public interface PrzejazdService {

    /**
     * Zapisuje nowy przejazd
     * @param przejazd encja przejazdu
     * @return zapisany przejazd
     */
    Przejazd savePrzejazd(Przejazd przejazd);

    /**
     * Zapisuje nowy przejazd na podstawie DTO
     * @param przejazdDto DTO przejazdu
     * @return zapisany przejazd
     */
    Przejazd savePrzejazd(PrzejazdDtoInput przejazdDto);
    
    /**
     * Aktualizuje przejazd na podstawie DTO
     * @param przejazdDto DTO przejazdu
     * @return zaktualizowany przejazd
     * @throws Exception wyjątek ogólny
     */
    Przejazd updatePrzejazd(PrzejazdDtoUpdate przejazdDto) throws Exception;
    
    /**
     * Znajduje przejazdy po kierowcy
     * @param kierowca kierowca
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByKierowca(Kierowca kierowca);

    /**
     * Znajduje przejazdy po autobusie
     * @param autobus autobus
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByAutobus(Autobus autobus);

    /**
     * Znajduje przejazdy po kursie
     * @param kurs kurs
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByKurs(Kurs kurs);

    /**
     * Znajduje przejazdy po spalaniu
     * @param spalanie spalanie
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdBySpalanie(float spalanie);

    /**
     * Znajduje przejazdy po spalaniu mniejszym niż podana wartość
     * @param spalanie spalanie
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdBySpalanieLessThan(float spalanie);

    /**
     * Znajduje przejazdy po spalaniu większym niż podana wartość
     * @param spalanie spalanie
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdBySpalanieGreaterThan(float spalanie);

    /**
     * Znajduje przejazdy po cenie za litr paliwa
     * @param cenaZaLitr cena za litr
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByCenaZaLitr(float cenaZaLitr);

    /**
     * Znajduje przejazdy po cenie za litr paliwa mniejszej niż podana wartość
     * @param cenaZaLitr cena za litr
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByCenaZaLitrLessThan(float cenaZaLitr);

    /**
     * Znajduje przejazdy po cenie za litr paliwa większej niż podana wartość
     * @param cenaZaLitr cena za litr
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByCenaZaLitrGreaterThan(float cenaZaLitr);

    /**
     * Znajduje przejazdy po długości trasy
     * @param dlugoscTrasy długość trasy
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDlugoscTrasy(float dlugoscTrasy);

    /**
     * Znajduje przejazdy po długości trasy mniejszej niż podana wartość
     * @param dlugoscTrasy długość trasy
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDlugoscTrasyLessThan(float dlugoscTrasy);

    /**
     * Znajduje przejazdy po długości trasy większej niż podana wartość
     * @param dlugoscTrasy długość trasy
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDlugoscTrasyGreaterThan(float dlugoscTrasy);

    /**
     * Znajduje przejazdy po dacie rozpoczęcia
     * @param dataStartu data rozpoczęcia
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDataStartu(Date dataStartu);

    /**
     * Znajduje przejazdy po dacie rozpoczęcia wcześniejszej niż podana wartość
     * @param dataStartu data rozpoczęcia
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDataStartuBefore(Date dataStartu);

    /**
     * Znajduje przejazdy po dacie rozpoczęcia późniejszej niż podana wartość
     * @param dataStartu data rozpoczęcia
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDataStartuAfter(Date dataStartu);

    /**
     * Znajduje przejazdy po dacie zakończenia
     * @param dataKonca data zakończenia
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDataKonca(Date dataKonca);

    /**
     * Znajduje przejazdy po dacie zakończenia wcześniejszej niż podana wartość
     * @param dataKonca data zakończenia
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDataKoncaBefore(Date dataKonca);

    /**
     * Znajduje przejazdy po dacie zakończenia późniejszej niż podana wartość
     * @param dataKonca data zakończenia
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdByDataKoncaAfter(Date dataKonca);

    /**
     * Znajduje przejazdy po kursie i dacie
     * @param kurs kurs
     * @param data data
     * @return lista przejazdów
     */
    List<Przejazd> findPrzejazdyByKursAndData(Kurs kurs, Date data);

    /**
     * Znajduje przejazd po ID
     * @param id ID przejazdu
     * @return Optional z przejazdem
     */
    Optional<Przejazd> findPrzejazdById(long id);
    
    /**
     * Znajduje wszystkie daty dla kursu
     * @param kurs kurs
     * @param count liczba dat do znalezienia
     * @return lista dat
     */
    List<Date> findAllDatesForKurs(Kurs kurs, int count);

    /**
     * Tworzy odłączony przejazd na podstawie kursu i daty przejazdu
     * @param kurs kurs
     * @param dataPrzejazdu data przejazdu
     * @return odłączony przejazd
     */
    Przejazd createDetachedPrzejazd(Kurs kurs, Date dataPrzejazdu);

}
