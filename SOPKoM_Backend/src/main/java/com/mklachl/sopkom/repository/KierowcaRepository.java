package com.mklachl.sopkom.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "SELECT * FROM kierowcy k WHERE k.kierowca_id NOT IN  (SELECT p.kierowca_id FROM przejazdy p WHERE p.data_startu < :endTime AND p.data_konca > :startTime)", nativeQuery = true)
    List<Kierowca> findAllByAvailability(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
