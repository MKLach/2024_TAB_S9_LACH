package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Harmonogram;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji Harmonogram
 */
public interface HarmonogramRepository extends CrudRepository<Harmonogram, Short> {

    /**
     * Znajduje wszystkie harmonogramy o określonej nazwie
     * @param nazwa nazwa
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByNazwa(String nazwa);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują w poniedziałki
     * @param pon true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByPon(boolean pon);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują we wtorki
     * @param wto true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByWto(boolean wto);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują w środy
     * @param sro true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllBySro(boolean sro);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują w czwartki
     * @param czw true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByCzw(boolean czw);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują w piątki
     * @param pia true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByPia(boolean pia);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują w soboty
     * @param sob true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllBySob(boolean sob);

    /**
     * Znajduje wszystkie harmonogramy, które obowiązują w niedziele
     * @param nie true jeśli obowiązuje, false w przeciwnym razie
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByNie(boolean nie);

    /**
     * Znajduje wszystkie harmonogramy o dodatkowych informacjach pasujących do wzorca
     * @param dodatkoweInf dodatkowe informacje
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAllByDodatkoweInfLike(String dodatkoweInf);

    /**
     * Znajduje wszystkie harmonogramy
     * @return Lista harmonogramów
     */
    List<Harmonogram> findAll();
}
