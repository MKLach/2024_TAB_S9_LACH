package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Autobus;
import java.util.Date;

/**
 * Data Transfer Object (DTO) dla encji Autobus.
 */
public class AutobusDto {

    /**
     * Identyfikator autobusu.
     */
    private Long autbousId;

    /**
     * Numer rejestracyjny autobusu.
     */
    private String numerRejestracyjny;

    /**
     * Data ważności przeglądu technicznego autobusu.
     */
    private Date przegladWaznyDo;

    /**
     * Status autobusu.
     */
    private String status;

    /**
     * Przebieg autobusu.
     */
    private float przebieg;

    /**
     * Konstruktor domyślny.
     */
    public AutobusDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Autobus.
     * @param autobus Obiekt encji Autobus.
     */
    public AutobusDto(Autobus autobus) {
        this.autbousId = autobus.getAutbousId();
        this.numerRejestracyjny = autobus.getNumerRejestracyjny();
        this.przegladWaznyDo = autobus.getPrzegladWaznyDo();
        this.status = autobus.getStatus();
        this.przebieg = autobus.getPrzebieg();
    }

    // Getters

    /**
     * Zwraca identyfikator autobusu.
     * @return autbousId Identyfikator autobusu.
     */
    public Long getAutbousId() {
        return autbousId;
    }

    /**
     * Zwraca numer rejestracyjny autobusu.
     * @return numerRejestracyjny Numer rejestracyjny autobusu.
     */
    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    /**
     * Zwraca datę ważności przeglądu technicznego autobusu.
     * @return przegladWaznyDo Data ważności przeglądu technicznego autobusu.
     */
    public Date getPrzegladWaznyDo() {
        return przegladWaznyDo;
    }

    /**
     * Zwraca status autobusu.
     * @return status Status autobusu.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Zwraca przebieg autobusu.
     * @return przebieg Przebieg autobusu.
     */
    public float getPrzebieg() {
        return przebieg;
    }

    // Setters

    /**
     * Ustawia identyfikator autobusu.
     * @param autbousId Identyfikator autobusu.
     */
    public void setAutbousId(Long autbousId) {
        this.autbousId = autbousId;
    }

    /**
     * Ustawia numer rejestracyjny autobusu.
     * @param numerRejestracyjny Numer rejestracyjny autobusu.
     */
    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    /**
     * Ustawia datę ważności przeglądu technicznego autobusu.
     * @param przegladWaznyDo Data ważności przeglądu technicznego autobusu.
     */
    public void setPrzegladWaznyDo(Date przegladWaznyDo) {
        this.przegladWaznyDo = przegladWaznyDo;
    }

    /**
     * Ustawia status autobusu.
     * @param status Status autobusu.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Ustawia przebieg autobusu.
     * @param przebieg Przebieg autobusu.
     */
    public void setPrzebieg(float przebieg) {
        this.przebieg = przebieg;
    }

    /**
     * Zwraca reprezentację obiektu w postaci łańcucha znaków.
     * @return Reprezentacja obiektu w postaci łańcucha znaków.
     */
    @Override
    public String toString() {
        return "AutobusDto [autbousId=" + autbousId + ", numerRejestracyjny=" + numerRejestracyjny
                + ", przegladWaznyDo=" + przegladWaznyDo + ", status=" + status + ", przebieg=" + przebieg + "]";
    }
}
