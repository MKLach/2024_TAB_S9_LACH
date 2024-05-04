package com.mklachl.sopkom.repository;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Autobus;

import java.util.Date;
import java.util.List;

public interface AutobusRepository extends CrudRepository<Autobus, Long>{

    Autobus findByNumerRejestracyjny(String numerRejestracyjny);

    Autobus findByPrzegladWaznyDoBefore(Date date);

    Autobus findByPrzegladWaznyDoAfter(Date date);

    Autobus findByStatus(String status);

    List<Autobus> findByPrzebiegGreaterThan(float przebieg);

    List<Autobus> findByPrzebiegLessThan(float przebieg);

    List<Autobus> findByPrzebieg(float przebieg);

}
