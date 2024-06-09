package com.mklachl.sopkom.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.entity.Incydent;
import java.util.Date;

/**
 * Data Transfer Object (DTO) dla encji Incydent.
 */
public class IncydentDto {

    /**
     * Identyfikator incydentu.
     */
    private Long incydentId;

    /**
     * Typ incydentu.
     */
    private String typ;

    /**
     * Data incydentu w formacie "dd.MM.yyyy".
     */
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    /**
     * Identyfikator autobusu związanego z incydentem.
     */
    private Long autobusId;

    /**
     * Identyfikator kierowcy związanego z incydentem.
     */
    private Long kierowcaId;

    /**
     * Identyfikator przejazdu związanego z incydentem.
     */
    private Long przejazdId;

    /**
     * Dodatkowe informacje dotyczące incydentu.
     */
    private String dodatkoweInformacje;

    /**
     * Krótki opis incydentu.
     */
    private String krotko;

    /**
     * Koszty związane z incydentem.
     */
    private float koszty;

    /**
     * Konstruktor domyślny.
     */
    public IncydentDto() {}

    /**
     * Konstruktor przyjmujący obiekt encji Incydent.
     * @param incydent Obiekt encji Incydent.
     */
    public IncydentDto(Incydent incydent) {
        this.krotko = incydent.getKrotko();
        this.incydentId = incydent.getIncydentId();
        this.typ = incydent.getTyp();
        this.date = incydent.getDate();
        this.autobusId = incydent.getAutobus() != null ? incydent.getAutobus().getAutbousId() : null;
        this.kierowcaId = incydent.getKierowca() != null ? incydent.getKierowca().getKierowcaId() : null;
        this.przejazdId = incydent.getPrzejazd() != null ? incydent.getPrzejazd().getPrzejazdId() : null;
        this.dodatkoweInformacje = incydent.getDodatkoweInformacje();
        this.koszty = incydent.getKoszty();
    }

    // Getters and Setters

    /**
     * Zwraca krótki opis incydentu.
     * @return krotko Krótki opis incydentu.
     */
    public String getKrotko() {
        return krotko;
    }

    /**
     * Ustawia krótki opis incydentu.
     * @param krotko Krótki opis incydentu.
     */
    public void setKrotko(String krotko) {
        this.krotko = krotko;
    }

    /**
     * Zwraca identyfikator incydentu.
     * @return incydentId Identyfikator incydentu.
     */
    public Long getIncydentId() {
        return incydentId;
    }

    /**
     * Ustawia identyfikator incydentu.
     * @param incydentId Identyfikator incydentu.
     */
    public void setIncydentId(Long incydentId) {
        this.incydentId = incydentId;
    }

    /**
     * Zwraca typ incydentu.
     * @return typ Typ incydentu.
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Ustawia typ incydentu.
     * @param typ Typ incydentu.
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * Zwraca datę incydentu.
     * @return date Data incydentu.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Ustawia datę incydentu.
     * @param date Data incydentu.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Zwraca identyfikator autobusu związanego z incydentem.
     * @return autobusId Identyfikator autobusu.
     */
    public Long getAutobusId() {
        return autobusId;
    }

    /**
     * Ustawia identyfikator autobusu związanego z incydentem.
     * @param autobusId Identyfikator autobusu.
     */
    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    /**
     * Zwraca identyfikator kierowcy związanego z incydentem.
     * @return kierowcaId Identyfikator kierowcy.
     */
    public Long getKierowcaId() {
        return kierowcaId;
    }

    /**
     * Ustawia identyfikator kierowcy związanego z incydentem.
     * @param kierowcaId Identyfikator kierowcy.
     */
    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    /**
     * Zwraca identyfikator przejazdu związanego z incydentem.
     * @return przejazdId Identyfikator przejazdu.
     */
    public Long getPrzejazdId() {
        return przejazdId;
    }

    /**
     * Ustawia identyfikator przejazdu związanego z incydentem.
     * @param przejazdId Identyfikator przejazdu.
     */
    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    /**
     * Zwraca dodatkowe informacje dotyczące incydentu.
     * @return dodatkoweInformacje Dodatkowe informacje dotyczące incydentu.
     */
    public String getDodatkoweInformacje() {
        return dodatkoweInformacje;
    }

    /**
     * Ustawia dodatkowe informacje dotyczące incydentu.
     * @param dodatkoweInformacje Dodatkowe informacje dotyczące incydentu.
     */
    public void setDodatkoweInformacje(String dodatkoweInformacje) {
        this.dodatkoweInformacje = dodatkoweInformacje;
    }

    /**
     * Zwraca koszty związane z incydentem.
     * @return koszty Koszty związane z incydentem.
     */
    public float getKoszty() {
        return koszty;
    }

    /**
     * Ustawia koszty związane z incydentem.
     * @param koszty Koszty związane z incydentem.
     */
    public void setKoszty(float koszty) {
        this.koszty = koszty;
    }
}
