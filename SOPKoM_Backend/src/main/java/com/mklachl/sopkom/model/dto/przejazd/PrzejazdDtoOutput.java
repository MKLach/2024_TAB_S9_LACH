package com.mklachl.sopkom.model.dto.przejazd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.PrzejazdKursPrzystanekWliniDto;
import com.mklachl.sopkom.model.entity.Przejazd;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) wyjściowy dla encji Przejazd.
 */
public class PrzejazdDtoOutput {

    /**
     * Identyfikator przejazdu.
     */
    private Long przejazdId;

    /**
     * Identyfikator kursu.
     */
    private Long kursId;

    /**
     * Numer linii.
     */
    private String liniaNumer;

    /**
     * Data startu w formacie "EE MMM d y H:m:s ZZZ", locale = "en-US".
     */
    @JsonFormat(pattern = "EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date dataStartu;

    /**
     * Data końca w formacie "EE MMM d y H:m:s ZZZ", locale = "en-US".
     */
    @JsonFormat(pattern = "EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date dataKonca;

    /**
     * Data w formacie "EE MMM d y H:m:s ZZZ", locale = "en-US".
     */
    @JsonFormat(pattern = "EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date data;

    /**
     * Imię i nazwisko kierowcy.
     */
    private String kierowcaImieNazwisko;

    /**
     * Nazwa autobusu.
     */
    private String autobusNazwa;

    /**
     * Numer rejestracyjny autobusu.
     */
    private String autobusNumerRejestracyjny;

    /**
     * Spalanie.
     */
    private float spalanie;

    /**
     * Cena za litr paliwa.
     */
    private float cenaZaLitr;

    /**
     * Długość trasy.
     */
    private float dlugoscTrasy;

    /**
     * Liczba biletów.
     */
    private int liczbaBiletow;

    /**
     * Status przejazdu.
     */
    private String status;

    /**
     * Kierunek kursu.
     */
    private short kierunek;

    /**
     * Lista przystanków w kursie.
     */
    private List<PrzejazdKursPrzystanekWliniDto> przystanki;

    /**
     * Konstruktor domyślny.
     */
    public PrzejazdDtoOutput() {}

    /**
     * Konstruktor przyjmujący obiekt encji Przejazd.
     * @param przejazd Obiekt encji Przejazd.
     */
    public PrzejazdDtoOutput(Przejazd przejazd) {
        this.przejazdId = przejazd.getPrzejazdId();
        this.kursId = przejazd.getKurs().getKursId();
        this.liniaNumer = przejazd.getKurs().getLinia().getNumer();
        this.dataStartu = przejazd.getDataStartu();
        this.dataKonca = przejazd.getDataKonca();
        this.kierowcaImieNazwisko = przejazd.getKierowca().getImie() + " " + przejazd.getKierowca().getNazwisko();
        this.autobusNazwa = przejazd.getAutobus().getNumerRejestracyjny();
        this.autobusNumerRejestracyjny = przejazd.getAutobus().getNumerRejestracyjny();
        this.spalanie = przejazd.getSpalanie();
        this.cenaZaLitr = przejazd.getCenaZaLitr();
        this.dlugoscTrasy = przejazd.getDlugoscTrasy();
        this.liczbaBiletow = przejazd.getPrzejazdBilet().size();
        this.kierunek = przejazd.getKurs().getKierunek().shortValue();
        Date now = new Date();
        this.data = przejazd.getData();
        this.przystanki = przejazd.getPrzejazdKursPrzystanekWlini().stream()
                .map(PrzejazdKursPrzystanekWliniDto::new).collect(Collectors.toList());
        this.status = "Zaplanowany";

        int count = 0;

        for (PrzejazdKursPrzystanekWliniDto przystanek : przystanki) {
            if (przystanek.getRealnaGodzinna() != null) {
                count++;
                this.status = "W trakcie";
            }
        }

        if (count == przystanki.size()) {
            this.status = "Zakończony";
        }

        if (now.after(przejazd.getDataKonca())) {
            if (status.equals("Zaplanowany")) {
                this.status = "Nie odbył się";
            } else if (status.equals("W trakcie")) {
                this.status = "Spóźniony";
            }
        }
    }

    /**
     * Konstruktor przyjmujący obiekt encji Przejazd oraz flagę.
     * @param przejazd Obiekt encji Przejazd.
     * @param a Flaga.
     */
    public PrzejazdDtoOutput(Przejazd przejazd, boolean a) {
        this.przejazdId = przejazd.getPrzejazdId();
        this.kursId = przejazd.getKurs().getKursId();
        this.liniaNumer = przejazd.getKurs().getLinia().getNumer();
        this.dataStartu = przejazd.getDataStartu();
        this.dataKonca = przejazd.getDataKonca();
        this.kierowcaImieNazwisko = null;
        this.autobusNazwa = null;
        this.autobusNumerRejestracyjny = null;
        this.spalanie = 0;
        this.cenaZaLitr = 0;
        this.dlugoscTrasy = 0;
        this.liczbaBiletow = 0;
        this.kierunek = przejazd.getKurs().getKierunek().shortValue();
        this.data = przejazd.getData();
        this.przystanki = przejazd.getPrzejazdKursPrzystanekWlini().stream()
                .map(PrzejazdKursPrzystanekWliniDto::new).collect(Collectors.toList());
        this.status = "Do zaplanowania";
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator przejazdu.
     * @return przejazdId Identyfikator przejazdu.
     */
    public Long getPrzejazdId() {
        return przejazdId;
    }

    /**
     * Ustawia identyfikator przejazdu.
     * @param przejazdId Identyfikator przejazdu.
     */
    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    /**
     * Zwraca identyfikator kursu.
     * @return kursId Identyfikator kursu.
     */
    public Long getKursId() {
        return kursId;
    }

    /**
     * Ustawia identyfikator kursu.
     * @param kursId Identyfikator kursu.
     */
    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    /**
     * Zwraca numer linii.
     * @return liniaNumer Numer linii.
     */
    public String getLiniaNumer() {
        return liniaNumer;
    }

    /**
     * Ustawia numer linii.
     * @param liniaNumer Numer linii.
     */
    public void setLiniaNumer(String liniaNumer) {
        this.liniaNumer = liniaNumer;
    }

    /**
     * Zwraca datę startu.
     * @return dataStartu Data startu.
     */
    public Date getDataStartu() {
        return dataStartu;
    }

    /**
     * Ustawia datę startu.
     * @param dataStartu Data startu.
     */
    public void setDataStartu(Date dataStartu) {
        this.dataStartu = dataStartu;
    }

    /**
     * Zwraca datę końca.
     * @return dataKonca Data końca.
     */
    public Date getDataKonca() {
        return dataKonca;
    }

    /**
     * Ustawia datę końca.
     * @param dataKonca Data końca.
     */
    public void setDataKonca(Date dataKonca) {
        this.dataKonca = dataKonca;
    }

    /**
     * Zwraca imię i nazwisko kierowcy.
     * @return kierowcaImieNazwisko Imię i nazwisko kierowcy.
     */
    public String getKierowcaImieNazwisko() {
        return kierowcaImieNazwisko;
    }

    /**
     * Ustawia imię i nazwisko kierowcy.
     * @param kierowcaImieNazwisko Imię i nazwisko kierowcy.
     */
    public void setKierowcaImieNazwisko(String kierowcaImieNazwisko) {
        this.kierowcaImieNazwisko = kierowcaImieNazwisko;
    }

    /**
     * Zwraca nazwę autobusu.
     * @return autobusNazwa Nazwa autobusu.
     */
    public String getAutobusNazwa() {
        return autobusNazwa;
    }

    /**
     * Ustawia nazwę autobusu.
     * @param autobusNazwa Nazwa autobusu.
     */
    public void setAutobusNazwa(String autobusNazwa) {
        this.autobusNazwa = autobusNazwa;
    }

    /**
     * Zwraca numer rejestracyjny autobusu.
     * @return autobusNumerRejestracyjny Numer rejestracyjny autobusu.
     */
    public String getAutobusNumerRejestracyjny() {
        return autobusNumerRejestracyjny;
    }

    /**
     * Ustawia numer rejestracyjny autobusu.
     * @param autobusNumerRejestracyjny Numer rejestracyjny autobusu.
     */
    public void setAutobusNumerRejestracyjny(String autobusNumerRejestracyjny) {
        this.autobusNumerRejestracyjny = autobusNumerRejestracyjny;
    }

    /**
     * Zwraca spalanie.
     * @return spalanie Spalanie.
     */
    public float getSpalanie() {
        return spalanie;
    }

    /**
     * Ustawia spalanie.
     * @param spalanie Spalanie.
     */
    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    /**
     * Zwraca cenę za litr paliwa.
     * @return cenaZaLitr Cena za litr paliwa.
     */
    public float getCenaZaLitr() {
        return cenaZaLitr;
    }

    /**
     * Ustawia cenę za litr paliwa.
     * @param cenaZaLitr Cena za litr paliwa.
     */
    public void setCenaZaLitr(float cenaZaLitr) {
        this.cenaZaLitr = cenaZaLitr;
    }

    /**
     * Zwraca długość trasy.
     * @return dlugoscTrasy Długość trasy.
     */
    public float getDlugoscTrasy() {
        return dlugoscTrasy;
    }

    /**
     * Ustawia długość trasy.
     * @param dlugoscTrasy Długość trasy.
     */
    public void setDlugoscTrasy(float dlugoscTrasy) {
        this.dlugoscTrasy = dlugoscTrasy;
    }

    /**
     * Zwraca liczbę biletów.
     * @return liczbaBiletow Liczba biletów.
     */
    public int getLiczbaBiletow() {
        return liczbaBiletow;
    }

    /**
     * Ustawia liczbę biletów.
     * @param liczbaBiletow Liczba biletów.
     */
    public void setLiczbaBiletow(int liczbaBiletow) {
        this.liczbaBiletow = liczbaBiletow;
    }

    /**
     * Zwraca status przejazdu.
     * @return status Status przejazdu.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Ustawia status przejazdu.
     * @param status Status przejazdu.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Zwraca listę przystanków w kursie.
     * @return przystanki Lista przystanków w kursie.
     */
    public List<PrzejazdKursPrzystanekWliniDto> getPrzystanki() {
        return przystanki;
    }

    /**
     * Ustawia listę przystanków w kursie.
     * @param przystanki Lista przystanków w kursie.
     */
    public void setPrzystanki(List<PrzejazdKursPrzystanekWliniDto> przystanki) {
        this.przystanki = przystanki;
    }

    /**
     * Zwraca datę.
     * @return data Data.
     */
    public Date getData() {
        return data;
    }

    /**
     * Ustawia datę.
     * @param data Data.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Zwraca kierunek kursu.
     * @return kierunek Kierunek kursu.
     */
    public short getKierunek() {
        return kierunek;
    }

    /**
     * Ustawia kierunek kursu.
     * @param kierunek Kierunek kursu.
     */
    public void setKierunek(short kierunek) {
        this.kierunek = kierunek;
    }

    
    /*public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/
}
