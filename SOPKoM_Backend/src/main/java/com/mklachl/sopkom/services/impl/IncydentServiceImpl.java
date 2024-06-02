package com.mklachl.sopkom.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mklachl.sopkom.exceptions.IncydentException;
import com.mklachl.sopkom.model.dto.IncydentDto;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Incydent;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.repository.IncydentRepository;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.services.IncydentService;

@Service
public class IncydentServiceImpl implements IncydentService {

	private final IncydentRepository incydentRepository;
    private final AutobusRepository autobusRepository;
    private final KierowcaRepository kierowcaRepository;
    private final PrzejazdRepository przejazdRepository;

    @Autowired
    public IncydentServiceImpl(IncydentRepository incydentRepository, AutobusRepository autobusRepository,
                               KierowcaRepository kierowcaRepository, PrzejazdRepository przejazdRepository) {
        this.incydentRepository = incydentRepository;
        this.autobusRepository = autobusRepository;
        this.kierowcaRepository = kierowcaRepository;
        this.przejazdRepository = przejazdRepository;
    }

    @Override
    public Incydent saveIncydent(Incydent incydentDto){
        Incydent incydent = new Incydent();
        incydent.setKrotko(incydentDto.getKrotko());
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

    public Incydent saveIncydent(IncydentDto incydentDto) throws IncydentException{
    	Incydent incydent = new Incydent();
        incydent.setTyp(incydentDto.getTyp());
        incydent.setDate(incydentDto.getDate());
        incydent.setKrotko(incydentDto.getKrotko());

        // Fetch and set Autobus
        if (incydentDto.getAutobusId() != null) {
            Autobus autobus = autobusRepository.findById(incydentDto.getAutobusId())
                    .orElseThrow(() -> new IncydentException("Autobus not found"));
            incydent.setAutobus(autobus);
        }

        // Fetch and set Kierowca
        if (incydentDto.getKierowcaId() != null) {
            Kierowca kierowca = kierowcaRepository.findById(incydentDto.getKierowcaId())
                    .orElseThrow(() -> new IncydentException("Kierowca not found"));
            incydent.setKierowca(kierowca);
        }

        // Fetch and set Przejazd
        if (incydentDto.getPrzejazdId() != null) {
            Przejazd przejazd = przejazdRepository.findById(incydentDto.getPrzejazdId())
                    .orElseThrow(() -> new IncydentException("Przejazd not found"));
            incydent.setPrzejazd(przejazd);
        }

        incydent.setDodatkoweInformacje(incydentDto.getDodatkoweInformacje());
        incydent.setKoszty(incydentDto.getKoszty());

        incydent = incydentRepository.save(incydent);
        return incydent;
    }
    
    @Override
    public Incydent updateIncydent(long id, IncydentDto updatedIncydentDto) throws IncydentException {
        // Find the incident by ID
        Incydent incydentToUpdate = incydentRepository.findById(id)
                .orElseThrow(() -> new IncydentException("Incydent not found"));

        incydentToUpdate.setDodatkoweInformacje("orignlany wpis o " + incydentToUpdate.getDate() + " "  + updatedIncydentDto.getDodatkoweInformacje());
        
        incydentToUpdate.setTyp(updatedIncydentDto.getTyp());
        incydentToUpdate.setDate(updatedIncydentDto.getDate());
        incydentToUpdate.setKrotko(updatedIncydentDto.getKrotko());

       
        if (updatedIncydentDto.getAutobusId() != null) {
            Autobus autobus = autobusRepository.findById(updatedIncydentDto.getAutobusId())
                    .orElseThrow(() -> new IncydentException("Autobus not found"));
            incydentToUpdate.setAutobus(autobus);
        }

        if (updatedIncydentDto.getKierowcaId() != null) {
            Kierowca kierowca = kierowcaRepository.findById(updatedIncydentDto.getKierowcaId())
                    .orElseThrow(() -> new IncydentException("Kierowca not found"));
            incydentToUpdate.setKierowca(kierowca);
        }

        if (updatedIncydentDto.getPrzejazdId() != null) {
            Przejazd przejazd = przejazdRepository.findById(updatedIncydentDto.getPrzejazdId())
                    .orElseThrow(() -> new IncydentException("Przejazd not found"));
            incydentToUpdate.setPrzejazd(przejazd);
        }

        incydentToUpdate.setKoszty(updatedIncydentDto.getKoszty());

        // Save the updated incident
        incydentToUpdate = incydentRepository.save(incydentToUpdate);
        
        return incydentToUpdate;
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
