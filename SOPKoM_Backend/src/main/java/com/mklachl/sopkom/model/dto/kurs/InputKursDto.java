package com.mklachl.sopkom.model.dto.kurs;

import java.util.List;

/**
 * Data Transfer Object (DTO) wejściowy dla encji Kurs.
 */
public class InputKursDto extends AbstractKursDto {

    /**
     * Lista przystanków w kursie.
     */
    public List<InputPrzystanekWKursieDto> przystanki;

    /**
     * Konstruktor domyślny.
     */
    public InputKursDto() {
    }

    // Getters and Setters

    /**
     * Zwraca listę przystanków w kursie.
     * @return przystanki Lista przystanków w kursie.
     */
    public List<InputPrzystanekWKursieDto> getPrzystanki() {
        return przystanki;
    }

    /**
     * Ustawia listę przystanków w kursie.
     * @param przystanki Lista przystanków w kursie.
     */
    public void setPrzystanki(List<InputPrzystanekWKursieDto> przystanki) {
        this.przystanki = przystanki;
    }
}
