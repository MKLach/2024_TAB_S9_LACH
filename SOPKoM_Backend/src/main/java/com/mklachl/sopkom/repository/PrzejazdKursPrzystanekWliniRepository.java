package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PrzejazdKursPrzystanekWliniRepository extends CrudRepository<PrzejazdKursPrzystanekWlini, Long> {

    List<PrzejazdKursPrzystanekWlini> findAllByPrzejazd(Przejazd przejazd);

    List<PrzejazdKursPrzystanekWlini> findAllByKursPrzystanekWlini(KursPrzystanekWlini kursPrzystanekWlini);

    List<PrzejazdKursPrzystanekWlini> findAllByRealnaGodzinna(Date realnaGodzinna);

    List<PrzejazdKursPrzystanekWlini> findAllByRealnaGodzinnaBefore(Date realnaGodzinna);

    List<PrzejazdKursPrzystanekWlini> findAllByRealnaGodzinnaAfter(Date realnaGodzinna);

    List<PrzejazdKursPrzystanekWlini> findAll();

}
