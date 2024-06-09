package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Bilet;

/**
 * Data Transfer Object (DTO) dla encji Bilet.
 */
public class BiletDto {

    /**
     * Identyfikator biletu.
     */
    private Short biletId;

    /**
     * Cena biletu.
     */
    private float cena;

    /**
     * Nazwa biletu.
     */
    private String nazwa;

    /**
     * Konstruktor domyślny.
     */
    public BiletDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Bilet.
     * @param bilet Obiekt encji Bilet.
     */
    public BiletDto(Bilet bilet) {
        this.biletId = bilet.getBiletId();
        this.cena = bilet.getCena();
        this.nazwa = bilet.getNazwa();
    }

    // Getters and Setters

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
     * @return cena Cena biletu.
     */
    public float getCena() {
        return cena;
    }

    /**
     * Ustawia cenę biletu.
     * @param cena Cena biletu.
     */
    public void setCena(float cena) {
        this.cena = cena;
    }

    /**
     * Zwraca nazwę biletu.
     * @return nazwa Nazwa biletu.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę biletu.
     * @param nazwa Nazwa biletu.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
