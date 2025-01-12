package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.dto.AutobusDto;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AutobusService {

    Autobus saveAutobus(Autobus autobus);

    Autobus saveAutobus(AutobusDto autobusDto);

    Autobus findAutobusByNumerRejestracyjny(String numerRejestracyjny);

    Optional<Autobus> findAutobusById(long id);

    Autobus findAutobusByPrzegladWaznyDoBefore(Date date);

    Autobus findAutobusByPrzegladWaznyDoAfter(Date date);

    List<Autobus> findAutobusByStatus(String status);

    List<Autobus> findAutobusByPrzebiegGreaterThan(float przebieg);

    List<Autobus> findAutobusByPrzebiegLessThan(float przebieg);

    List<Autobus> findAutobusByPrzebieg(float przebieg);

    List<Autobus> findAutobusByAvailable(Date startTime, Date endTime);

}
