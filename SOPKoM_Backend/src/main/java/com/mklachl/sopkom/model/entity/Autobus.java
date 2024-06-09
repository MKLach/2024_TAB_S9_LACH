package com.mklachl.sopkom.model.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;

/**
 * Klasa reprezentująca encję Autobus.
 */
@Entity
@Table(name = "autobusy")
public class Autobus {

    /** 
     * Unikalny identyfikator autobusu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autbous_id")
    private Long autbousId;

    /** 
     * Numer rejestracyjny autobusu, wymagane pole.
     */
    @Column(name = "numer_rejestracyjny", nullable = false, length = 10)
    private String numerRejestracyjny;

    /** 
     * Data ważności przeglądu technicznego, wymagane pole.
     */
    @Column(name = "przegladwaznydo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date przegladWaznyDo;

    /** 
     * Status autobusu, wymagane pole.
     */
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    /** 
     * Przebieg autobusu, wymagane pole.
     */
    @Column(name = "przebieg", nullable = false)
    private float przebieg;

    // Getters

    /** 
     * Pobiera unikalny identyfikator autobusu.
     * @return Unikalny identyfikator autobusu.
     */
    public Long getAutbousId() {
        return autbousId;
    }

    /** 
     * Pobiera numer rejestracyjny autobusu.
     * @return Numer rejestracyjny autobusu.
     */
    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    /** 
     * Pobiera datę ważności przeglądu technicznego.
     * @return Data ważności przeglądu technicznego.
     */
    public Date getPrzegladWaznyDo() {
        return przegladWaznyDo;
    }

    /** 
     * Pobiera status autobusu.
     * @return Status autobusu.
     */
    public String getStatus() {
        return status;
    }

    /** 
     * Pobiera przebieg autobusu.
     * @return Przebieg autobusu.
     */
    public float getPrzebieg() {
        return przebieg;
    }

    // Setters

    /** 
     * Ustawia unikalny identyfikator autobusu.
     * @param autbousId Unikalny identyfikator autobusu.
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
     * Ustawia datę ważności przeglądu technicznego.
     * @param przegladWaznyDo Data ważności przeglądu technicznego.
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
}
