package com.mklachl.sopkom.repository;
import org.springframework.data.repository.CrudRepository;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;

import java.util.Date;
import java.util.List;

public interface KierowcaRepository extends CrudRepository<Kierowca, Long>{
    Kierowca findByPesel(String pesel);
    
    List<Kierowca> findAllByPeselLike(String pesel);

    Kierowca findByUser(User user);

    List<Kierowca> findAllByImieAndNazwisko(String imie, String nazwisko);
    
    List<Kierowca> findAllByImieAndNazwiskoLike(String imie, String nazwisko);

    List<Kierowca> findAllByPrawoJazdyWazneDoBefore(Date date);

    List<Kierowca> findAllByPrawoJazdyWazneDoAfter(Date date);
    
    List<Kierowca> findAll();

	List<Kierowca> findByNazwiskoLike(String nazwisko);    
}
