package com.mklachl.sopkom.model.dto.kurs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;
import java.sql.Time;

/**
 * Abstrakcyjna klasa Data Transfer Object (DTO) dla encji KursPrzystanekWlini.
 */
public class AbstractPrzystanekWKursieDto {

    /**
     * Identyfikator przystanku w kursie.
     */
    protected Long przystanekwKursieId;

    /**
     * Godzina przystanku w formacie "hh:MM:ss".
     */
    @JsonFormat(pattern = "hh:MM:ss")
    protected Time godzinna;

    /**
     * Konstruktor domyślny.
     */
    public AbstractPrzystanekWKursieDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji KursPrzystanekWlini.
     * @param kpw Obiekt encji KursPrzystanekWlini.
     */
    public AbstractPrzystanekWKursieDto(KursPrzystanekWlini kpw) {
        this.godzinna = new Time(kpw.getGodzinna().getTime());
        this.przystanekwKursieId = kpw.getKursPrzystanekWliniId();
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator przystanku w kursie.
     * @return przystanekwKursieId Identyfikator przystanku w kursie.
     */
    public Long getPrzystanekwKursieId() {
        return przystanekwKursieId;
    }

    /**
     * Ustawia identyfikator przystanku w kursie.
     * @param przystanekwKursieId Identyfikator przystanku w kursie.
     */
    public void setPrzystanekwKursieId(Long przystanekwKursieId) {
        this.przystanekwKursieId = przystanekwKursieId;
    }

    /**
     * Zwraca godzinę przystanku.
     * @return godzinna Godzina przystanku.
     */
    public Time getGodzinna() {
        return godzinna;
    }

    /**
     * Ustawia godzinę przystanku.
     * @param godzinna Godzina przystanku.
     */
    public void setGodzinna(Time godzinna) {
        this.godzinna = godzinna;
    }
}
