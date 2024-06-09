package com.mklachl.sopkom.model.dto.przejazd;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Data Transfer Object (DTO) dla aktualizacji encji PrzejazdKursPrzystanekWLinii.
 */
public class PKPWLDtoUpdate {

    /**
     * Identyfikator encji.
     */
    private Long id;

    /**
     * Data w formacie "HH:mm:ss, dd.MM.yyyy", z ustaloną strefą czasową "UTC".
     */
    @JsonFormat(pattern = "HH:mm:ss, dd.MM.yyyy", timezone = "UTC")
    private Date date;

    /**
     * Konstruktor domyślny.
     */
    public PKPWLDtoUpdate() {
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator encji.
     * @return id Identyfikator encji.
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia identyfikator encji.
     * @param id Identyfikator encji.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca datę.
     * @return date Data.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Ustawia datę.
     * @param date Data.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
