package com.mklachl.sopkom.services.impl;

import org.springframework.stereotype.Service;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.services.AutobusService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AutobusServiceImpl implements AutobusService {
    private AutobusRepository autobusRepository;

    public AutobusServiceImpl(AutobusRepository autobusRepository) {
        this.autobusRepository = autobusRepository;
    }

    public Autobus saveAutobus(AutobusDto autobusDto) {
        Autobus autobus = new Autobus();
        autobus.setNumerRejestracyjny(autobusDto.getNumerRejestracyjny());
        autobus.setPrzebieg(autobusDto.getPrzebieg());
        autobus.setPrzegladWaznyDo(autobusDto.getPrzegladWaznyDo());
        autobus.setStatus(autobusDto.getStatus());

        autobus = autobusRepository.save(autobus);
        return autobus;
    }

    @Override
    public Autobus saveAutobus(Autobus autobusDto) {
        Autobus autobus = new Autobus();
        autobus.setNumerRejestracyjny(autobusDto.getNumerRejestracyjny());
        autobus.setPrzebieg(autobusDto.getPrzebieg());
        autobus.setPrzegladWaznyDo(autobusDto.getPrzegladWaznyDo());
        autobus.setStatus(autobusDto.getStatus());

        autobus = autobusRepository.save(autobus);
        return autobus;
    }

    @Override
    public Autobus findAutobusByNumerRejestracyjny(String numerRejestracyjny){
        return autobusRepository.findByNumerRejestracyjny(numerRejestracyjny);
    }

    @Override
    public Optional<Autobus> findAutobusById(long id){
        return autobusRepository.findById(id);
    }

    @Override
    public Autobus findAutobusByPrzegladWaznyDoBefore(Date date){
        return autobusRepository.findByPrzegladWaznyDoBefore(date);
    }

    @Override
    public Autobus findAutobusByPrzegladWaznyDoAfter(Date date){
        return autobusRepository.findByPrzegladWaznyDoAfter(date);
    }

    @Override
    public List<Autobus> findAutobusByStatus(String status){
        return autobusRepository.findAllByStatus(status);
    }

    @Override
    public List<Autobus> findAutobusByPrzebiegGreaterThan(float przebieg){
        return autobusRepository.findAllByPrzebiegGreaterThan(przebieg);
    }

    @Override
    public List<Autobus> findAutobusByPrzebiegLessThan(float przebieg){
        return autobusRepository.findAllByPrzebiegLessThan(przebieg);
    }

    @Override
    public List<Autobus> findAutobusByPrzebieg(float przebieg){
        return autobusRepository.findAllByPrzebieg(przebieg);
    }

    @Override
    public List<Autobus> findAutobusByAvailable(Date startTime, Date endTime){ return autobusRepository.findAllByAvailable(startTime, endTime); }

}
