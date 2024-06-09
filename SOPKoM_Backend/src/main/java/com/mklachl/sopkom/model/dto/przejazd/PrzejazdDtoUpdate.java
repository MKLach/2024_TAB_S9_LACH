package com.mklachl.sopkom.model.dto.przejazd;

import java.util.List;

/**
 * Data Transfer Object (DTO) dla aktualizacji encji Przejazd.
 */
public class PrzejazdDtoUpdate {

    /**
     * Identyfikator przejazdu.
     */
    private long przejazdId;

    /**
     * Spalanie.
     */
    private double spalanie;

    /**
     * Cena za litr paliwa.
     */
    private double cenaZaLitr;

    /**
     * Długość trasy.
     */
    private double dlugoszTrasy;

    /**
     * Liczba biletów ulgowych.
     */
    private int liczbaBiletowUlgowych;

    /**
     * Liczba biletów normalnych.
     */
    private int liczbaBiletowNormalnych;

    /**
     * Lista przystanków w kursie do aktualizacji.
     */
    private List<PKPWLDtoUpdate> przystanki;

    /**
     * Konstruktor domyślny.
     */
    public PrzejazdDtoUpdate() {}

    // Getters and Setters

    /**
     * Zwraca identyfikator przejazdu.
     * @return przejazdId Identyfikator przejazdu.
     */
    public long getPrzejazdId() {
        return przejazdId;
    }

    /**
     * Ustawia identyfikator przejazdu.
     * @param przejazdId Identyfikator przejazdu.
     */
    public void setPrzejazdId(long przejazdId) {
        this.przejazdId = przejazdId;
    }

    /**
     * Zwraca spalanie.
     * @return spalanie Spalanie.
     */
    public double getSpalanie() {
        return spalanie;
    }

    /**
     * Ustawia spalanie.
     * @param spalanie Spalanie.
     */
    public void setSpalanie(double spalanie) {
        this.spalanie = spalanie;
    }

    /**
     * Zwraca cenę za litr paliwa.
     * @return cenaZaLitr Cena za litr paliwa.
     */
    public double getCenaZaLitr() {
        return cenaZaLitr;
    }

    /**
     * Ustawia cenę za litr paliwa.
     * @param cenaZaLitr Cena za litr paliwa.
     */
    public void setCenaZaLitr(double cenaZaLitr) {
        this.cenaZaLitr = cenaZaLitr;
    }

    /**
     * Zwraca długość trasy.
     * @return dlugoszTrasy Długość trasy.
     */
    public double getDlugoszTrasy() {
        return dlugoszTrasy;
    }

    /**
     * Ustawia długość trasy.
     * @param dlugoszTrasy Długość trasy.
     */
    public void setDlugoszTrasy(double dlugoszTrasy) {
        this.dlugoszTrasy = dlugoszTrasy;
    }

    /**
     * Zwraca liczbę biletów ulgowych.
     * @return liczbaBiletowUlgowych Liczba biletów ulgowych.
     */
    public int getLiczbaBiletowUlgowych() {
        return liczbaBiletowUlgowych;
    }

    /**
     * Ustawia liczbę biletów ulgowych.
     * @param liczbaBiletowUlgowych Liczba biletów ulgowych.
     */
    public void setLiczbaBiletowUlgowych(int liczbaBiletowUlgowych) {
        this.liczbaBiletowUlgowych = liczbaBiletowUlgowych;
    }

    /**
     * Zwraca liczbę biletów normalnych.
     * @return liczbaBiletowNormalnych Liczba biletów normalnych.
     */
    public int getLiczbaBiletowNormalnych() {
        return liczbaBiletowNormalnych;
    }

    /**
     * Ustawia liczbę biletów normalnych.
     * @param liczbaBiletowNormalnych Liczba biletów normalnych.
     */
    public void setLiczbaBiletowNormalnych(int liczbaBiletowNormalnych) {
        this.liczbaBiletowNormalnych = liczbaBiletowNormalnych;
    }

    /**
     * Zwraca listę przystanków w kursie do aktualizacji.
     * @return przystanki Lista przystanków w kursie do aktualizacji.
     */
    public List<PKPWLDtoUpdate> getPrzystanki() {
        return przystanki;
    }

    /**
     * Ustawia listę przystanków w kursie do aktualizacji.
     * @param przystanki Lista przystanków w kursie do aktualizacji.
     */
    public void setPrzystanki(List<PKPWLDtoUpdate> przystanki) {
        this.przystanki = przystanki;
    }

    @Override
    public String toString() {
        return "PrzejazdDtoUpdate [przejazdId=" + przejazdId + ", spalanie=" + spalanie + ", cenaZaLitr=" + cenaZaLitr
                + ", dlugoszTrasy=" + dlugoszTrasy + ", liczbaBiletowUlgowych=" + liczbaBiletowUlgowych
                + ", liczbaBiletowNormalnych=" + liczbaBiletowNormalnych + ", przystanki=" + przystanki + "]";
    }
}
