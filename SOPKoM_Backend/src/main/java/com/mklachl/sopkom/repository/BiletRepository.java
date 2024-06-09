package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Bilet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfejs repository dla encji Bilet
 */
public interface BiletRepository extends CrudRepository<Bilet, Short> {

    /**
     * Znajduje wszystkie bilety o określonej cenie
     * @param cena cena
     * @return Lista biletów
     */
    List<Bilet> findAllByCena(float cena);

    /**
     * Znajduje wszystkie bilety o określonej nazwie
     * @param nazwa nazwa
     * @return Lista biletów
     */
    List<Bilet> findAllByNazwa(String nazwa);

    /**
     * Znajduje wszystkie bilety
     * @return Lista biletów
     */
    List<Bilet> findAll();
}
