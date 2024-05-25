package com.mklachl.sopkom.services.impl;

import org.springframework.stereotype.Service;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.services.PrzejazdService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrzejazdServiceImpl implements PrzejazdService {
    private PrzejazdRepository przejazdRepository;

    public PrzejazdServiceImpl(PrzejazdRepository przejazdRepository) {
        this.przejazdRepository = przejazdRepository;
    }

    @Override
    public Przejazd savePrzejazd(Przejazd przejazdDto) {
        Przejazd przejazd = new Przejazd();

        przejazd.setKierowca(przejazdDto.getKierowca());
        przejazd.setAutobus(przejazdDto.getAutobus());
        przejazd.setKurs(przejazdDto.getKurs());
        przejazd.setSpalanie(przejazdDto.getSpalanie());
        przejazd.setCenaZaLitr(przejazdDto.getCenaZaLitr());
        przejazd.setDlugoscTrasy(przejazdDto.getDlugoscTrasy());
        przejazd.setDataStartu(przejazdDto.getDataStartu());
        przejazd.setDataKonca(przejazdDto.getDataKonca());
        przejazd.setPrzejazdKursPrzystanekWlini(przejazdDto.getPrzejazdKursPrzystanekWlini());
        przejazd.setPrzejazdBilet(przejazdDto.getPrzejazdBilet());

        przejazd = przejazdRepository.save(przejazd);
        return przejazd;
    }

    /*public Przejazd savePrzejazd(PrzejazdDto przejazdDto) {
        Przejazd przejazd = new Przejazd();

        przejazd.setKierowca(przejazdDto.getKierowca());
        przejazd.setAutobus(przejazdDto.getAutobus());
        przejazd.setKurs(przejazdDto.getKurs());
        przejazd.setSpalanie(przejazdDto.getSpalanie());
        przejazd.setCenaZaLitr(przejazdDto.getCenaZaLitr());
        przejazd.setDlugoscTrasy(przejazdDto.getDlugoscTrasy());
        przejazd.setDataStartu(przejazdDto.getDataStartu());
        przejazd.setDataKonca(przejazdDto.getDataKonca());
        przejazd.setPrzejazdKursPrzystanekWlini(przejazdDto.getPrzejazdKursPrzystanekWlini());
        przejazd.setPrzejazdBilet(przejazdDto.getPrzejazdBilet());

        przejazd = przejazdRepository.save(przejazd);
        return przejazd;
    }*/

    @Override
    public List<Przejazd> findPrzejazdByKierowca(Kierowca kierowca){return przejazdRepository.findAllByKierowca(kierowca);}

    @Override
    public List<Przejazd> findPrzejazdByAutobus(Autobus autobus){return przejazdRepository.findAllByAutobus(autobus);}

    @Override
    public List<Przejazd> findPrzejazdByKurs(Kurs kurs){return przejazdRepository.findAllByKurs(kurs);}

    @Override
    public List<Przejazd> findPrzejazdBySpalanie(float spalanie){return przejazdRepository.findAllBySpalanie(spalanie);}

    @Override
    public List<Przejazd> findPrzejazdBySpalanieLessThan(float spalanie){return przejazdRepository.findAllBySpalanieLessThan(spalanie);}

    @Override
    public List<Przejazd> findPrzejazdBySpalanieGreaterThan(float spalanie){return przejazdRepository.findAllBySpalanieGreaterThan(spalanie);}

    @Override
    public List<Przejazd> findPrzejazdByCenaZaLitr(float cenaZaLitr){return przejazdRepository.findAllByCenaZaLitr(cenaZaLitr);}

    @Override
    public List<Przejazd> findPrzejazdByCenaZaLitrLessThan(float cenaZaLitr){return przejazdRepository.findAllByCenaZaLitrLessThan(cenaZaLitr);}

    @Override
    public List<Przejazd> findPrzejazdByCenaZaLitrGreaterThan(float cenaZaLitr){return przejazdRepository.findAllByCenaZaLitrGreaterThan(cenaZaLitr);}

    @Override
    public List<Przejazd> findPrzejazdByDlugoscTrasy(float dlugoscTrasy){return przejazdRepository.findAllByDlugoscTrasy(dlugoscTrasy);}

    @Override
    public List<Przejazd> findPrzejazdByDlugoscTrasyLessThan(float dlugoscTrasy){return przejazdRepository.findAllByDlugoscTrasyLessThan(dlugoscTrasy);}

    @Override
    public List<Przejazd> findPrzejazdByDlugoscTrasyGreaterThan(float dlugoscTrasy){return przejazdRepository.findAllByDlugoscTrasyGreaterThan(dlugoscTrasy);}

    @Override
    public List<Przejazd> findPrzejazdByDataStartu(Date dataStartu){return przejazdRepository.findAllByDataStartu(dataStartu);}

    @Override
    public List<Przejazd> findPrzejazdByDataStartuBefore(Date dataStartu){return przejazdRepository.findAllByDataStartuBefore(dataStartu);}

    @Override
    public List<Przejazd> findPrzejazdByDataStartuAfter(Date dataStartu){return przejazdRepository.findAllByDataStartuAfter(dataStartu);}

    @Override
    public List<Przejazd> findPrzejazdByDataKonca(Date dataKonca){return przejazdRepository.findAllByDataKonca(dataKonca);}

    @Override
    public List<Przejazd> findPrzejazdByDataKoncaBefore(Date dataKonca){return przejazdRepository.findAllByDataKoncaBefore(dataKonca);}

    @Override
    public List<Przejazd> findPrzejazdByDataKoncaAfter(Date dataKonca){return przejazdRepository.findAllByDataKoncaAfter(dataKonca);}

    @Override
    public Optional<Przejazd> findPrzejazdById(long id){return przejazdRepository.findById(id);}

    @Override
    public List<Przejazd> findPrzejazdByData(Date data){
        List<Przejazd> list = new ArrayList<>();
        return list;
    }
}
