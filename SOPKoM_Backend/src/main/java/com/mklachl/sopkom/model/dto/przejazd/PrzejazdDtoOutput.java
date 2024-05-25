package com.mklachl.sopkom.model.dto.przejazd;

import com.mklachl.sopkom.model.dto.PrzejazdKursPrzystanekWliniDto;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PrzejazdDtoOutput {
    private Long przejazdId;
    private Long kursId;
    private String liniaNumer;
    private Date dataStartu;
    private Date dataKonca;
    private String kierowcaImieNazwisko;
    private String autobusNazwa;
    private String autobusNumerRejestracyjny;
    private float spalanie;
    private float cenaZaLitr;
    private float dlugoscTrasy;
    private int liczbaBiletow;
    private String status;
    private List<PrzejazdKursPrzystanekWliniDto> przystanki;

    public PrzejazdDtoOutput() {}

    public PrzejazdDtoOutput(Przejazd przejazd) {
        this.przejazdId = przejazd.getPrzejazdId();
        this.kursId = przejazd.getKurs().getKursId();
        this.liniaNumer = przejazd.getKurs().getLinia().getNumer();
        this.dataStartu = przejazd.getDataStartu();
        this.dataKonca = przejazd.getDataKonca();
        this.kierowcaImieNazwisko = przejazd.getKierowca().getImie() + " " + przejazd.getKierowca().getNazwisko();
        this.autobusNazwa = przejazd.getAutobus().getNumerRejestracyjny(); //do sprawdzenia
        this.autobusNumerRejestracyjny = przejazd.getAutobus().getNumerRejestracyjny();
        this.spalanie = przejazd.getSpalanie();
        this.cenaZaLitr = przejazd.getCenaZaLitr();
        this.dlugoscTrasy = przejazd.getDlugoscTrasy();
        this.liczbaBiletow = przejazd.getPrzejazdBilet().size();
        Date now = new Date();
        if (przejazd.getDataStartu() != null && przejazd.getDataKonca() != null) {
            if (now.before(przejazd.getDataStartu())) {
                this.status = "zapl";
            } else if (now.after(przejazd.getDataKonca())) {
                this.status = "zako";
            } else {
                this.status = "trwa";
            }
        } else {
            this.status = "none";
        }
        this.przystanki = przejazd.getPrzejazdKursPrzystanekWlini().stream()
                .map(PrzejazdKursPrzystanekWliniDto::new).collect(Collectors.toList());

    }

    // Getters and Setters
    public Long getPrzejazdId() {
        return przejazdId;
    }

    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    public Long getKursId() {
        return kursId;
    }

    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public String getLiniaNumer() {
        return liniaNumer;
    }

    public void setLiniaNumer(String liniaNumer) {
        this.liniaNumer = liniaNumer;
    }

    public Date getDataStartu() {
        return dataStartu;
    }

    public void setDataStartu(Date dataStartu) {
        this.dataStartu = dataStartu;
    }

    public Date getDataKonca() {
        return dataKonca;
    }

    public void setDataKonca(Date dataKonca) {
        this.dataKonca = dataKonca;
    }

    public String getKierowcaImieNazwisko() {
        return kierowcaImieNazwisko;
    }

    public void setKierowcaImieNazwisko(String kierowcaImieNazwisko) {
        this.kierowcaImieNazwisko = kierowcaImieNazwisko;
    }

    public String getAutobusNazwa() {
        return autobusNazwa;
    }

    public void setAutobusNazwa(String autobusNazwa) {
        this.autobusNazwa = autobusNazwa;
    }

    public String getAutobusNumerRejestracyjny() {
        return autobusNumerRejestracyjny;
    }

    public void setAutobusNumerRejestracyjny(String autobusNumerRejestracyjny) {
        this.autobusNumerRejestracyjny = autobusNumerRejestracyjny;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    public float getCenaZaLitr() {
        return cenaZaLitr;
    }

    public void setCenaZaLitr(float cenaZaLitr) {
        this.cenaZaLitr = cenaZaLitr;
    }

    public float getDlugoscTrasy() {
        return dlugoscTrasy;
    }

    public void setDlugoscTrasy(float dlugoscTrasy) {
        this.dlugoscTrasy = dlugoscTrasy;
    }

    public int getLiczbaBiletow() {
        return liczbaBiletow;
    }

    public void setLiczbaBiletow(int liczbaBiletow) {
        this.liczbaBiletow = liczbaBiletow;
    }

    /*public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/
}
