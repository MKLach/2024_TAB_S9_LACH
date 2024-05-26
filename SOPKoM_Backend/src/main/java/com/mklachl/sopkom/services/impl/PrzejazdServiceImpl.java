package com.mklachl.sopkom.services.impl;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoInput;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;
import com.mklachl.sopkom.raporty.rozkład.DateHelper;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.repository.PrzejazdKursPrzystanekWliniRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.services.PrzejazdService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrzejazdServiceImpl implements PrzejazdService {
	
	private KierowcaRepository kierowcaRepository;
	private AutobusRepository autobusRepository;
	private KursRepository kursRepository;
	
    private PrzejazdRepository przejazdRepository;
    private PrzejazdKursPrzystanekWliniRepository pkpwlRepository;
 

    public PrzejazdServiceImpl(KierowcaRepository kierowcaRepository, AutobusRepository autobusRepository,
			KursRepository kursRepository, PrzejazdRepository przejazdRepository, PrzejazdKursPrzystanekWliniRepository pkpwlRepository) {
		
		this.kierowcaRepository = kierowcaRepository;
		this.autobusRepository = autobusRepository;
		this.kursRepository = kursRepository;
		this.przejazdRepository = przejazdRepository;
		this.pkpwlRepository = pkpwlRepository;
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
    public List<Przejazd> findPrzejazdyByKursAndData(Kurs kurs, Date data){ return przejazdRepository.findAllByKursAndData(kurs, data); }

	@Override
	public Przejazd savePrzejazd(PrzejazdDtoInput przejazdDto) {
		
		Przejazd przejazd = new Przejazd();
		
		przejazd.setKierowca(kierowcaRepository.findById(przejazdDto.getKierowcaId()).get());
		przejazd.setAutobus(autobusRepository.findById(przejazdDto.getAutobusId()).get());
		
		Kurs kurs = kursRepository.findById(przejazdDto.getKursId()).get();
		
		przejazd.setKurs(kurs);
		
		przejazd.setData(przejazdDto.getDataPrzejazdu());
		
		
		
		var list = new ArrayList<PrzejazdKursPrzystanekWlini>();
		
		var list2 = kurs.getKursPrzystanekWlinii();
		
		if(kurs.getKierunek().shortValue() == 0) {
			
			Date startRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(0).getGodzinna(), 
					przejazdDto.getDataPrzejazdu());
			
			przejazd.setDataStartu( DateHelper.addMinutesToDate(startRaw, -30));
					
			
			Date endRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(list2.size()-1).getGodzinna(),
					przejazdDto.getDataPrzejazdu());
			
			endRaw = DateHelper.addMinutesToDate(endRaw, 30);
			
			if(endRaw.before(startRaw)) {
				endRaw = DateHelper.addDays(endRaw, 1);
			}
			
			przejazd.setDataKonca(endRaw);
			
		} else {
			Date startRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(list2.size()-1).getGodzinna(), 
					przejazdDto.getDataPrzejazdu());
			
			przejazd.setDataStartu( DateHelper.addMinutesToDate(startRaw, -30));
					
			
			Date endRaw = DateHelper.copyTimeComponents(
					kurs.getKursPrzystanekWlinii().get(0).getGodzinna(),
					przejazdDto.getDataPrzejazdu());
			
			endRaw = DateHelper.addMinutesToDate(endRaw, 30);
			
			if(endRaw.before(startRaw)) {
				endRaw = DateHelper.addDays(endRaw, 1);
			}
			
			przejazd.setDataKonca(endRaw);
			
			przejazd.setDataKonca(DateHelper.addMinutesToDate(endRaw, 30));
		}
		
		przejazdRepository.save(przejazd);
		
		for(int i = 0; i < list2.size(); i++) {
			
			KursPrzystanekWlini kpwl = list2.get(i);
			PrzejazdKursPrzystanekWlini pkpwl = new PrzejazdKursPrzystanekWlini();
			pkpwl.setKursPrzystanekWlini(kpwl);
			pkpwl.setRealnaGodzinna(null);
			pkpwl.setPrzejazd(przejazd);
			
			pkpwl = pkpwlRepository.save(pkpwl);
			list.add(pkpwl);
			
		}
		
		przejazd.setPrzejazdKursPrzystanekWlini(list);
		przejazdRepository.save(przejazd);
		
		
		return przejazd;
	}


}
