package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Bilet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BiletRepository extends CrudRepository<Bilet, Short> {

    List<Bilet> findAllByCena(float cena);

    List<Bilet> findAllByNazwa(String nazwa);

    List<Bilet> findAll();

}
