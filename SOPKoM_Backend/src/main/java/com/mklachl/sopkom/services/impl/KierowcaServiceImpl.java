package com.mklachl.sopkom.services.impl;

import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.services.KierowcaService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class KierowcaServiceImpl implements KierowcaService {
    private KierowcaRepository kierowcaRepository;

    public KierowcaServiceImpl(KierowcaRepository kierowcaRepository) {
        this.kierowcaRepository = kierowcaRepository;
    }

    @Override
    public Kierowca saveKierowca(Kierowca kierowcaDto) {
        Kierowca kierowca = new Kierowca();
        kierowca.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
        kierowca.setImie(kierowcaDto.getImie());
        kierowca.setNazwisko(kierowcaDto.getNazwisko());
        kierowca.setPesel(kierowcaDto.getPesel());
        kierowca.setUser(kierowcaDto.getUser());

        kierowcaRepository.save(kierowca);
        return kierowca;
    }

    public Kierowca saveKierowca(KierowcaDto kierowcaDto) {
        Kierowca kierowca = new Kierowca();
        kierowca.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
        kierowca.setImie(kierowcaDto.getImie());
        kierowca.setNazwisko(kierowcaDto.getNazwisko());
        kierowca.setPesel(kierowcaDto.getPesel());
        kierowca.setUser(kierowcaDto.getUser());

        kierowcaRepository.save(kierowca);
        return kierowca;
    }

    @Override
    public Kierowca findKierowcaByPesel(String pesel){
        return kierowcaRepository.findByPesel(pesel);
    }

    @Override
    public Kierowca findKierowcaByUser(User user){
        return kierowcaRepository.findByUser(user);
    }

    @Override
    public List<Kierowca> findKierowcaByImieAndNazwisko(String imie, String nazwisko){
        return kierowcaRepository.findByImieAndNazwisko(imie, nazwisko);
    }

    @Override
    public List<Kierowca> findKierowcaByPrawoJazdyWazneDoBefore(Date date){
        return kierowcaRepository.findByPrawoJazdyWazneDoAfter(date);
    }

    @Override
    public List<Kierowca> findKierowcaByPrawoJazdyWazneDoAfter(Date date){
        return kierowcaRepository.findByPrawoJazdyWazneDoAfter(date);
    }

    @Override
    public Optional<Kierowca> findKierowcaById(Long id){
        return kierowcaRepository.findById(id);
    }
}
