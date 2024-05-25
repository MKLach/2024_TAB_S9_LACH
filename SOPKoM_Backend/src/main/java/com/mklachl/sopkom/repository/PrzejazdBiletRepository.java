package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Bilet;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdBilet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrzejazdBiletRepository extends CrudRepository<PrzejazdBilet, Long> {

    List<PrzejazdBilet> findAllByPrzejazd(Przejazd przejazd);

    List<PrzejazdBilet> findAllByBilet(Bilet bilet);

    List<PrzejazdBilet> findAllByCenaBiletuGreaterThan(float cenaBiletu);

    List<PrzejazdBilet> findAllByCenaBiletuLessThan(float cenaBiletu);

    List<PrzejazdBilet> findAllByCenaBiletu(float cenaBiletu);

    List<PrzejazdBilet> findAll();

}
