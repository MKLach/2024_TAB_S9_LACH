package com.mklachl.sopkom.repository;

import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Linia;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs repository dla encji Linia
 */
public interface LiniaRepository extends CrudRepository<Linia, Long> {

    /**
     * Znajduje linię po identyfikatorze
     * @param id identyfikator
     * @return Optional z linią
     */
    Optional<Linia> findById(Long id);

    /**
     * Znajduje linię po numerze
     * @param numer numer
     * @return Linia
     */
    Linia findByNumer(String numer);

    /**
     * Znajduje wszystkie linie
     * @return Lista linii
     */
    List<Linia> findAll();
}
