package com.mklachl.sopkom.repository;
import com.mklachl.sopkom.model.entity.Harmonogram;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
public interface HarmonogramRepository extends CrudRepository<Harmonogram, Short>{

    List<Harmonogram> findAllByNazwa(String nazwa);

    List<Harmonogram> findAllByPon(boolean pon);

    List<Harmonogram> findAllByWto(boolean wto);

    List<Harmonogram> findAllBySro(boolean sro);

    List<Harmonogram> findAllByCzw(boolean czw);

    List<Harmonogram> findAllByPia(boolean pia);

    List<Harmonogram> findAllBySob(boolean sob);

    List<Harmonogram> findAllByNie(boolean nie);

    List<Harmonogram> findAllByDodatkoweInfLike(String dodatkoweInf);

    List<Harmonogram> findAll();
}
