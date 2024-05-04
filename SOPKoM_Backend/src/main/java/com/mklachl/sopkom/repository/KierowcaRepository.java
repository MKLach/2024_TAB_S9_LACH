package com.mklachl.sopkom.repository;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;

import java.util.Date;
import java.util.List;

public interface KierowcaRepository extends CrudRepository<Kierowca, Long>{
    Kierowca findByPesel(String pesel);

    Kierowca findByUser(User user);

    List<Kierowca> findByImieAndNazwisko(String imie, String nazwisko);

    List<Kierowca> findByPrawoJazdyWazneDoBefore(Date date);

    List<Kierowca> findByPrawoJazdyWazneDoAfter(Date date);
}
