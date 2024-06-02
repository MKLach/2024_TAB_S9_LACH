package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.IncydentDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IncydentService {



    Incydent saveIncydent(Incydent incydent);

    Incydent saveIncydent(IncydentDto incydentDto);

    Optional<Incydent> findIncydentById(long id);

    List<Incydent> findIncydentByTyp(String typ);

    List<Incydent> findIncydentByDateBefore(Date date);

    List<Incydent> findIncydentByDateAfter(Date date);

    List<Incydent> findIncydentByDateBetween(Date date1, Date date2);

    List<Incydent> findIncydentByDate(Date date);

    List<Incydent> findIncydentByAutobus(Autobus autobus);

    List<Incydent> findIncydentByKierowca(Kierowca kierowca);

    List<Incydent> findIncydentByPrzejazd(Przejazd przejazd);

    List<Incydent> findIncydentByDodatkoweInformacje(String dodatkoweInformacje);

    List<Incydent> findIncydentByKosztyLessThan(float koszty);

    List<Incydent> findIncydentByKosztyGreaterThan(float koszty);

    List<Incydent> findIncydentByKoszty(float koszty);

}
