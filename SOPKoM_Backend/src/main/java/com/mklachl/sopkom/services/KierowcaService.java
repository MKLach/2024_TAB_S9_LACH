package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KierowcaService {

    Kierowca saveKierowca(Kierowca kierowca);

    Kierowca saveKierowca(KierowcaDto kierowcaDto);

    Kierowca findKierowcaByPesel(String pesel);

    Kierowca findKierowcaByUser(User user);

    List<Kierowca> findKierowcaByImieAndNazwisko(String imie, String nazwisko);

    List<Kierowca> findKierowcaByPrawoJazdyWazneDoBefore(Date date);

    List<Kierowca> findKierowcaByPrawoJazdyWazneDoAfter(Date date);

    Optional<Kierowca> findKierowcaById(Long id);

}
