package com.mklachl.sopkom.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;
import java.util.Date;

/**
 * Data Transfer Object (DTO) dla encji PrzejazdKursPrzystanekWlini.
 */
public class PrzejazdKursPrzystanekWliniDto {

    /**
     * Identyfikator PrzejazdKursPrzystanekWlini.
     */
    private Long przejazdKursPrzystanekWliniId;

    /**
     * Kolejność przystanku.
     */
    private int kolejnosc;

    /**
     * Przewidywana godzina przystanku w formacie "dd-MM-yyyy'T'HH:mm:ss -02:00".
     */
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss -02:00")
    private Date przewidywanaGodzinna;

    /**
     * Realna godzina przystanku w formacie "EE MMM d y H:m:s ZZZ".
     */
    @JsonFormat(pattern = "EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date realnaGodzinna;

    /**
     * Nazwa przystanku.
     */
    private String nazwa;

    /**
     * Identyfikator przystanku.
     */
    private Long przystanekId;

    /**
     * Konstruktor domyślny.
     */
    public PrzejazdKursPrzystanekWliniDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji PrzejazdKursPrzystanekWlini.
     * @param przejazdKursPrzystanekWlini Obiekt encji PrzejazdKursPrzystanekWlini.
     */
    public PrzejazdKursPrzystanekWliniDto(PrzejazdKursPrzystanekWlini przejazdKursPrzystanekWlini) {
        this.przejazdKursPrzystanekWliniId = przejazdKursPrzystanekWlini.getPrzejazdKursPrzystanekWliniId();
        this.kolejnosc = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getPrzystanekWlini().getKolejnosc();
        this.przewidywanaGodzinna = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getGodzinna();
        this.realnaGodzinna = przejazdKursPrzystanekWlini.getRealnaGodzinna();
        this.nazwa = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getPrzystanekWlini().getPrzystanek().getNazwa();
        this.przystanekId = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getPrzystanekWlini().getPrzystanek().getPrzystanekId();
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator PrzejazdKursPrzystanekWlini.
     * @return przejazdKursPrzystanekWliniId Identyfikator PrzejazdKursPrzystanekWlini.
     */
    public Long getPrzejazdKursPrzystanekWliniId() {
        return przejazdKursPrzystanekWliniId;
    }

    /**
     * Ustawia identyfikator PrzejazdKursPrzystanekWlini.
     * @param przejazdKursPrzystanekWliniId Identyfikator PrzejazdKursPrzystanekWlini.
     */
    public void setPrzejazdKursPrzystanekWliniId(Long przejazdKursPrzystanekWliniId) {
        this.przejazdKursPrzystanekWliniId = przejazdKursPrzystanekWliniId;
    }

    /**
     * Zwraca kolejność przystanku.
     * @return kolejnosc Kolejność przystanku.
     */
    public int getKolejnosc() {
        return kolejnosc;
    }

    /**
     * Ustawia kolejność przystanku.
     * @param kolejnosc Kolejność przystanku.
     */
    public void setKolejnosc(int kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    /**
     * Zwraca przewidywaną godzinę przystanku.
     * @return przewidywanaGodzinna Przewidywana godzina przystanku.
     */
    public Date getPrzewidywanaGodzinna() {
        return przewidywanaGodzinna;
    }

    /**
     * Ustawia przewidywaną godzinę przystanku.
     * @param przewidywanaGodzinna Przewidywana godzina przystanku.
     */
    public void setPrzewidywanaGodzinna(Date przewidywanaGodzinna) {
        this.przewidywanaGodzinna = przewidywanaGodzinna;
    }

    /**
     * Zwraca realną godzinę przystanku.
     * @return realnaGodzinna Realna godzina przystanku.
     */
    public Date getRealnaGodzinna() {
        return realnaGodzinna;
    }

    /**
     * Ustawia realną godzinę przystanku.
     * @param realnaGodzinna Realna godzina przystanku.
     */
    public void setRealnaGodzinna(Date realnaGodzinna) {
        this.realnaGodzinna = realnaGodzinna;
    }

    /**
     * Zwraca nazwę przystanku.
     * @return nazwa Nazwa przystanku.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę przystanku.
     * @param nazwa Nazwa przystanku.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

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
}
