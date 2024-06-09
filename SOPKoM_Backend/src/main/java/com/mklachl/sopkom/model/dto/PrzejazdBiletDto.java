package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.PrzejazdBilet;

/**
 * Data Transfer Object (DTO) dla encji PrzejazdBilet.
 */
public class PrzejazdBiletDto {

    /**
     * Identyfikator PrzejazdBilet.
     */
    private Long przejazdBiletId;

    /**
     * Identyfikator przejazdu.
     */
    private Long przejazdId;

    /**
     * Identyfikator biletu.
     */
    private Short biletId;

    /**
     * Cena biletu.
     */
    private float cenaBiletu;

    /**
     * Konstruktor domyślny.
     */
    public PrzejazdBiletDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji PrzejazdBilet.
     * @param przejazdBilet Obiekt encji PrzejazdBilet.
     */
    public PrzejazdBiletDto(PrzejazdBilet przejazdBilet) {
        this.przejazdBiletId = przejazdBilet.getPrzejazdBiletId();
        this.przejazdId = przejazdBilet.getPrzejazd().getPrzejazdId();
        this.biletId = przejazdBilet.getBilet().getBiletId();
        this.cenaBiletu = przejazdBilet.getCenaBiletu();
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator PrzejazdBilet.
     * @return przejazdBiletId Identyfikator PrzejazdBilet.
     */
    public Long getPrzejazdBiletId() {
        return przejazdBiletId;
    }

    /**
     * Ustawia identyfikator PrzejazdBilet.
     * @param przejazdBiletId Identyfikator PrzejazdBilet.
     */
    public void setPrzejazdBiletId(Long przejazdBiletId) {
        this.przejazdBiletId = przejazdBiletId;
    }

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
     * Zwraca identyfikator biletu.
     * @return biletId Identyfikator biletu.
     */
    public Short getBiletId() {
        return biletId;
    }

    /**
     * Ustawia identyfikator biletu.
     * @param biletId Identyfikator biletu.
     */
    public void setBiletId(Short biletId) {
        this.biletId = biletId;
    }

    /**
     * Zwraca cenę biletu.
     * @return cenaBiletu Cena biletu.
     */
    public float getCenaBiletu() {
        return cenaBiletu;
    }

    /**
     * Ustawia cenę biletu.
     * @param cenaBiletu Cena biletu.
     */
    public void setCenaBiletu(float cenaBiletu) {
        this.cenaBiletu = cenaBiletu;
    }
}
