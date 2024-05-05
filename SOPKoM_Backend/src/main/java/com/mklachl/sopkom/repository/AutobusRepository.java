package com.mklachl.sopkom.repository;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Autobus;

import java.util.Date;
import java.util.List;

public interface AutobusRepository extends CrudRepository<Autobus, Long>{

    Autobus findByNumerRejestracyjny(String numerRejestracyjny);

    Autobus findByPrzegladWaznyDoBefore(Date date);

    Autobus findByPrzegladWaznyDoAfter(Date date);

    List<Autobus> findAllByStatus(String status);

    List<Autobus> findAllByPrzebiegGreaterThan(float przebieg);

    List<Autobus> findAllByPrzebiegLessThan(float przebieg);

    List<Autobus> findAllByPrzebieg(float przebieg);
    
    List<Autobus> findByStatusLike(String status);

    List<Autobus> findAllByNumerRejestracyjnyLike(String numerRejestracyjny);

    List<Autobus> findAll();    
}
