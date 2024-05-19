package com.mklachl.sopkom.repository;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

import java.util.Date;
import java.util.List;

public interface KursPrzystanekWliniRepository extends CrudRepository<KursPrzystanekWlini, Long>{

    List<KursPrzystanekWlini> findAllByKurs(Kurs kurs);

    List<KursPrzystanekWlini> findAllByPrzystanekWlini(PrzystanekWlini przystanekWlini);

    List<KursPrzystanekWlini> findAllByGodzinnaAfter(Date date);

    List<KursPrzystanekWlini> findAllByGodzinnaBefore(Date date);

    List<KursPrzystanekWlini> findAllByGodzinna(Date date);

    List<KursPrzystanekWlini> findAll();
}
