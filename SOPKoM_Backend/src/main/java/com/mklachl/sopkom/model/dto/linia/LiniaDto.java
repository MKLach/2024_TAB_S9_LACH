package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.entity.Linia;

/**
 * Data Transfer Object (DTO) dla encji Linia.
 */
public class LiniaDto {

    /**
     * Identyfikator linii.
     */
    protected Long id;

    /**
     * Numer linii.
     */
    protected String numer;

    /**
     * Konstruktor domyślny.
     */
    public LiniaDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Linia.
     * @param linia Obiekt encji Linia.
     */
    public LiniaDto(Linia linia) {
        this.id = linia.getLiniaId();
        this.numer = linia.getNumer();
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator linii.
     * @return id Identyfikator linii.
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia identyfikator linii.
     * @param id Identyfikator linii.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca numer linii.
     * @return numer Numer linii.
     */
    public String getNumer() {
        return numer;
    }

    /**
     * Ustawia numer linii.
     * @param numer Numer linii.
     */
    public void setNumer(String numer) {
        this.numer = numer;
    }
}
