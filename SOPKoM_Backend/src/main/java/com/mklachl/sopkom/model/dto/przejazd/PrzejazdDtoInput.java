package com.mklachl.sopkom.model.dto.przejazd;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data Transfer Object (DTO) wejściowy dla encji Przejazd.
 */
public class PrzejazdDtoInput {

    /**
     * Identyfikator kursu.
     */
    private Long kursId;

    /**
     * Identyfikator kierowcy.
     */
    private Long kierowcaId;

    /**
     * Identyfikator autobusu.
     */
    private Long autobusId;

    /**
     * Data przejazdu w formacie "dd.MM.yyyy".
     */
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dataPrzejazdu;

    /**
     * Konstruktor domyślny.
     */
    public PrzejazdDtoInput() {}

    // Getters and Setters

    /**
     * Zwraca identyfikator kursu.
     * @return kursId Identyfikator kursu.
     */
    public Long getKursId() {
        return kursId;
    }

    /**
     * Ustawia identyfikator kursu.
     * @param kursId Identyfikator kursu.
     */
    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    /**
     * Zwraca identyfikator kierowcy.
     * @return kierowcaId Identyfikator kierowcy.
     */
    public Long getKierowcaId() {
        return kierowcaId;
    }

    /**
     * Ustawia identyfikator kierowcy.
     * @param kierowcaId Identyfikator kierowcy.
     */
    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    /**
     * Zwraca identyfikator autobusu.
     * @return autobusId Identyfikator autobusu.
     */
    public Long getAutobusId() {
        return autobusId;
    }

    /**
     * Ustawia identyfikator autobusu.
     * @param autobusId Identyfikator autobusu.
     */
    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    /**
     * Zwraca datę przejazdu.
     * @return dataPrzejazdu Data przejazdu.
     */
    public Date getDataPrzejazdu() {
        return dataPrzejazdu;
    }

    /**
     * Ustawia datę przejazdu.
     * @param dataPrzejazdu Data przejazdu.
     */
    public void setDataPrzejazdu(Date dataPrzejazdu) {
        this.dataPrzejazdu = dataPrzejazdu;
    }

    @Override
    public String toString() {
        return "PrzejazdDtoInput [kursId=" + kursId + ", kierowcaId=" + kierowcaId + ", autobusId=" + autobusId
                + ", dataPrzejazdu=" + dataPrzejazdu + "]";
    }
}
