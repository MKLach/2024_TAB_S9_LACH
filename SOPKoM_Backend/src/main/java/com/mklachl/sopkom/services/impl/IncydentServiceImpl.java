package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.dto.IncydentDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.repository.IncydentRepository;
import com.mklachl.sopkom.services.IncydentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class IncydentServiceImpl implements IncydentService {

    private IncydentRepository incydentRepository;

    IncydentServiceImpl(IncydentRepository incydentRepository) {
        this.incydentRepository = incydentRepository;
    }

    @Override
    public Incydent saveIncydent(Incydent incydentDto){
        Incydent incydent = new Incydent();
        incydent.setTyp(incydentDto.getTyp());
        incydent.setDate(incydentDto.getDate());
        incydent.setAutobus(incydentDto.getAutobus());
        incydent.setKierowca(incydentDto.getKierowca());
        incydent.setPrzejazd(incydentDto.getPrzejazd());
        incydent.setDodatkoweInformacje(incydentDto.getDodatkoweInformacje());
        incydent.setKoszty(incydentDto.getKoszty());

        incydent = incydentRepository.save(incydent);
        return incydent;
    }

    public Incydent saveIncydent(IncydentDto incydentDto){
        Incydent incydent = new Incydent();
        incydent.setTyp(incydentDto.getTyp());
        incydent.setDate(incydentDto.getDate());
        //incydent.setAutobus(incydentDto.getAutobus());
        //incydent.setKierowca(incydentDto.getKierowca());
        //incydent.setPrzejazd(incydentDto.getPrzejazd());
        incydent.setDodatkoweInformacje(incydentDto.getDodatkoweInformacje());
        incydent.setKoszty(incydentDto.getKoszty());

        incydent = incydentRepository.save(incydent);
        return incydent;
    }

    @Override
    public Optional<Incydent> findIncydentById(long id){
        return incydentRepository.findById(id);
    }

    @Override
    public List<Incydent> findIncydentByTyp(String typ){
        return incydentRepository.findAllByTyp(typ);
    }

    @Override
    public List<Incydent> findIncydentByDateBefore(Date date){
        return incydentRepository.findAllByDateBefore(date);
    }

    @Override
    public List<Incydent> findIncydentByDateAfter(Date date){
        return incydentRepository.findAllByDateAfter(date);
    }

    @Override
    public List<Incydent> findIncydentByDateBetween(Date date1, Date date2){
        return incydentRepository.findAllByDateBetween(date1, date2);
    }

    @Override
    public List<Incydent> findIncydentByDate(Date date){
        return incydentRepository.findAllByDate(date);
    }

    @Override
    public List<Incydent> findIncydentByAutobus(Autobus autobus){
        return incydentRepository.findAllByAutobus(autobus);
    }

    @Override
    public List<Incydent> findIncydentByKierowca(Kierowca kierowca){
        return incydentRepository.findAllByKierowca(kierowca);
    }

    @Override
    public List<Incydent> findIncydentByPrzejazd(Przejazd przejazd){
        return incydentRepository.findAllByPrzejazd(przejazd);
    }

    @Override
    public List<Incydent> findIncydentByDodatkoweInformacje(String dodatkoweInformacje){
        return incydentRepository.findAllByDodatkoweInformacje(dodatkoweInformacje);
    }

    @Override
    public List<Incydent> findIncydentByKosztyLessThan(float koszty){
        return incydentRepository.findAllByKosztyLessThan(koszty);
    }

    @Override
    public List<Incydent> findIncydentByKosztyGreaterThan(float koszty){
        return incydentRepository.findAllByKosztyGreaterThan(koszty);
    }

    @Override
    public List<Incydent> findIncydentByKoszty(float koszty){
        return incydentRepository.findAllByKoszty(koszty);
    }
}
