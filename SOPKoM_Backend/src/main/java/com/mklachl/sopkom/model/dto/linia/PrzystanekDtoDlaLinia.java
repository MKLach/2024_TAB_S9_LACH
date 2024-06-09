package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.dto.PrzystanekDtoFull;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;

/**
 * Data Transfer Object (DTO) dla encji PrzystanekWlini, używany w kontekście linii.
 */
public class PrzystanekDtoDlaLinia extends PrzystanekDtoFull {

    /**
     * Kolejność przystanku w linii.
     */
    private Short kolejnosc;

    /**
     * Adres przystanku (miasto i ulica).
     */
    private String adres;

    /**
     * Identyfikator przystanku w linii.
     */
    private Long przystanekwliniId;

    /**
     * Konstruktor domyślny.
     */
    public PrzystanekDtoDlaLinia() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji PrzystanekWlini.
     * @param przystanekWlini Obiekt encji PrzystanekWlini.
     */
    public PrzystanekDtoDlaLinia(PrzystanekWlini przystanekWlini) {
        super(przystanekWlini.getPrzystanek());
        this.przystanekwliniId = przystanekWlini.getPrzystanekWliniId();
        this.kolejnosc = przystanekWlini.getKolejnosc();
        this.adres = przystanekWlini.getPrzystanek().getMiasto() + ", " + przystanekWlini.getPrzystanek().getUlica();
    }

    // Getters and Setters

    /**
     * Zwraca kolejność przystanku w linii.
     * @return kolejnosc Kolejność przystanku.
     */
    public Short getKolejnosc() {
        return kolejnosc;
    }

    /**
     * Ustawia kolejność przystanku w linii.
     * @param kolejnosc Kolejność przystanku.
     */
    public void setKolejnosc(Short kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    /**
     * Zwraca adres przystanku.
     * @return adres Adres przystanku.
     */
    public String getAdres() {
        return adres;
    }

    /**
     * Ustawia adres przystanku.
     * @param adres Adres przystanku.
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * Zwraca identyfikator przystanku w linii.
     * @return przystanekwliniId Identyfikator przystanku w linii.
     */
    public Long getPrzystanekwliniId() {
        return przystanekwliniId;
    }

    /**
     * Ustawia identyfikator przystanku w linii.
     * @param przystanekwliniId Identyfikator przystanku w linii.
     */
    public void setPrzystanekwliniId(Long przystanekwliniId) {
        this.przystanekwliniId = przystanekwliniId;
    }
}
