package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoInput;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoUpdate;
import com.mklachl.sopkom.model.entity.Autobus;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Przejazd;

import java.util.Date;
import java.util.List;
import java.util.Optional;
//import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDto;

public interface PrzejazdService {

    Przejazd savePrzejazd(Przejazd przejazd);

    Przejazd savePrzejazd(PrzejazdDtoInput przejazdDto);
    
    Przejazd updatePrzejazd(PrzejazdDtoUpdate przejazdDto) throws Exception;
    
    List<Przejazd> findPrzejazdByKierowca(Kierowca kierowca);

    List<Przejazd> findPrzejazdByAutobus(Autobus autobus);

    List<Przejazd> findPrzejazdByKurs(Kurs kurs);

    List<Przejazd> findPrzejazdBySpalanie(float spalanie);

    List<Przejazd> findPrzejazdBySpalanieLessThan(float spalanie);

    List<Przejazd> findPrzejazdBySpalanieGreaterThan(float spalanie);

    List<Przejazd> findPrzejazdByCenaZaLitr(float cenaZaLitr);

    List<Przejazd> findPrzejazdByCenaZaLitrLessThan(float cenaZaLitr);

    List<Przejazd> findPrzejazdByCenaZaLitrGreaterThan(float cenaZaLitr);

    List<Przejazd> findPrzejazdByDlugoscTrasy(float dlugoscTrasy);

    List<Przejazd> findPrzejazdByDlugoscTrasyLessThan(float dlugoscTrasy);

    List<Przejazd> findPrzejazdByDlugoscTrasyGreaterThan(float dlugoscTrasy);

    List<Przejazd> findPrzejazdByDataStartu(Date dataStartu);

    List<Przejazd> findPrzejazdByDataStartuBefore(Date dataStartu);

    List<Przejazd> findPrzejazdByDataStartuAfter(Date dataStartu);

    List<Przejazd> findPrzejazdByDataKonca(Date dataKonca);

    List<Przejazd> findPrzejazdByDataKoncaBefore(Date dataKonca);

    List<Przejazd> findPrzejazdByDataKoncaAfter(Date dataKonca);

    List<Przejazd> findPrzejazdyByKursAndData(Kurs kurs, Date data);

    Optional<Przejazd> findPrzejazdById(long id);

}
