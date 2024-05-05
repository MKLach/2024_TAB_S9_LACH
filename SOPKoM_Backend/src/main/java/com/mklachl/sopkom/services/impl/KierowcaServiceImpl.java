package com.mklachl.sopkom.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.dto.UserDto;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.services.KierowcaService;
import com.mklachl.sopkom.services.UserService;

public class KierowcaServiceImpl implements KierowcaService {
    private KierowcaRepository kierowcaRepository;
    private UserService userService;

    public KierowcaServiceImpl(KierowcaRepository kierowcaRepository,UserService userService) {
        this.kierowcaRepository = kierowcaRepository;
        this.userService = userService;
    }

    @Override
    public Kierowca saveKierowca(Kierowca kierowcaDto) {
        Kierowca kierowca = new Kierowca();
        kierowca.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
        kierowca.setImie(kierowcaDto.getImie());
        kierowca.setNazwisko(kierowcaDto.getNazwisko());
        kierowca.setPesel(kierowcaDto.getPesel());
        kierowca.setUser(kierowcaDto.getUser());

        kierowca=kierowcaRepository.save(kierowca);
        return kierowca;
    }

    public Kierowca saveKierowca(KierowcaDto kierowcaDto) {
        Kierowca kierowca = new Kierowca();
        kierowca.setPrawoJazdyWazneDo(kierowcaDto.getPrawoJazdyWazneDo());
        kierowca.setImie(kierowcaDto.getImie());
        kierowca.setNazwisko(kierowcaDto.getNazwisko());
        kierowca.setPesel(kierowcaDto.getPesel());
        
        if(kierowcaDto.getUser() != null) {
        	 User userIn = userService.findUserById(kierowcaDto.getUser().getId());
        	 kierowca.setUser(userIn);

        }
        
        kierowca = kierowcaRepository.save(kierowca);
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
        return kierowcaRepository.findAllByImieAndNazwisko(imie, nazwisko);
    }

    @Override
    public List<Kierowca> findKierowcaByPrawoJazdyWazneDoBefore(Date date){
        return kierowcaRepository.findAllByPrawoJazdyWazneDoAfter(date);
    }

    @Override
    public List<Kierowca> findKierowcaByPrawoJazdyWazneDoAfter(Date date){
        return kierowcaRepository.findAllByPrawoJazdyWazneDoAfter(date);
    }

    @Override
    public Optional<Kierowca> findKierowcaById(Long id){
        return kierowcaRepository.findById(id);
    }
}
