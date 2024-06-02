package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface IncydentRepository extends CrudRepository<Incydent, Long> {

    List<Incydent> findAllByTyp(String typ);

    List<Incydent> findAllByDateBefore(Date date);

    List<Incydent> findAllByDateAfter(Date date);

    List<Incydent> findAllByDateBetween(Date date1, Date date2);

    List<Incydent> findAllByDate(Date date);

    List<Incydent> findAllByAutobus(Autobus autobus);

    List<Incydent> findAllByKierowca(Kierowca kierowca);

    List<Incydent> findAllByPrzejazd(Przejazd przejazd);

    List<Incydent> findAllByDodatkoweInformacje(String dodatkoweInformacje);

    List<Incydent> findAllByKosztyLessThan(float koszty);

    List<Incydent> findAllByKosztyGreaterThan(float koszty);

    List<Incydent> findAllByKoszty(float koszty);

    List<Incydent> findAll();

}
