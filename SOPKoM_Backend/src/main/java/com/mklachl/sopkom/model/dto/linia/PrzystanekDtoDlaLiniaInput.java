package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.entity.PrzystanekWlini;

/**
 * Data Transfer Object (DTO) wejściowy dla encji PrzystanekWlini, używany w kontekście linii.
 */
public class PrzystanekDtoDlaLiniaInput {

    /**
     * Identyfikator przystanku.
     */
    private Long przystanekId;

    /**
     * Kolejność przystanku w linii.
     */
    private Short kolejnosc;

    /**
     * Konstruktor domyślny.
     */
    public PrzystanekDtoDlaLiniaInput() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji PrzystanekWlini.
     * @param przystanek Obiekt encji PrzystanekWlini.
     */
    public PrzystanekDtoDlaLiniaInput(PrzystanekWlini przystanek) {
        this.przystanekId = przystanek.getPrzystanek().getPrzystanekId();
        this.kolejnosc = przystanek.getKolejnosc();
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator przystanku.
     * @return przystanekId Identyfikator przystanku.
     */
    public Long getPrzystanekId() {
        return przystanekId;
    }

    /**
     * Ustawia identyfikator przystanku.
     * @param przystanekId Identyfikator przystanku.
     */
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

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
}
