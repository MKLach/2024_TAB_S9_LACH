package com.mklachl.sopkom.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Data Transfer Object (DTO) dla obiektu opakowującego datę.
 */
public class DateWrapperDto {

    /**
     * Data w formacie "dd.MM.yyyy".
     */
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    /**
     * Konstruktor przyjmujący datę.
     * @param date Data.
     */
    public DateWrapperDto(Date date) {
        this.date = date;
    }

    /**
     * Konstruktor domyślny.
     */
    public DateWrapperDto() {
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
