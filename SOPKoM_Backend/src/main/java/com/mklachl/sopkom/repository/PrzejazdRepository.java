package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Przejazd;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PrzejazdRepository extends CrudRepository<Przejazd, Long> {

    List<Przejazd> findAllByKierowca(Kierowca kierowca);

    List<Przejazd> findAllByAutobus(Autobus autobus);

    List<Przejazd> findAllByKurs(Kurs kurs);

    List<Przejazd> findAllBySpalanie(float spalanie);

    List<Przejazd> findAllBySpalanieLessThan(float spalanie);

    List<Przejazd> findAllBySpalanieGreaterThan(float spalanie);

    List<Przejazd> findAllByCenaZaLitr(float cenaZaLitr);

    List<Przejazd> findAllByCenaZaLitrLessThan(float cenaZaLitr);

    List<Przejazd> findAllByCenaZaLitrGreaterThan(float cenaZaLitr);

    List<Przejazd> findAllByDlugoscTrasy(float dlugoscTrasy);

    List<Przejazd> findAllByDlugoscTrasyLessThan(float dlugoscTrasy);

    List<Przejazd> findAllByDlugoscTrasyGreaterThan(float dlugoscTrasy);

    List<Przejazd> findAllByDataStartu(Date dataStartu);

    List<Przejazd> findAllByDataStartuBefore(Date dataStartu);

    List<Przejazd> findAllByDataStartuAfter(Date dataStartu);

    List<Przejazd> findAllByDataKonca(Date dataKonca);

    List<Przejazd> findAllByDataKoncaBefore(Date dataKonca);

    List<Przejazd> findAllByDataKoncaAfter(Date dataKonca);

    List<Przejazd> findAll();

}