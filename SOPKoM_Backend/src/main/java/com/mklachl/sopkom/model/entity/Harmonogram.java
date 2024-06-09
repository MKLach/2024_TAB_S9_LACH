package com.mklachl.sopkom.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Klasa reprezentująca encję Harmonogram.
 */
@Entity
@Table(name = "harmonogramy")
public class Harmonogram {

    /**
     * Identyfikator harmonogramu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hamonogram_id")
    private Short hamonogramId;

    /**
     * Nazwa harmonogramu.
     */
    @Column(name = "nazwa", length = 50)
    private String nazwa;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje w poniedziałek.
     */
    @Column(name = "pon", nullable = false)
    private boolean pon;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje we wtorek.
     */
    @Column(name = "wto", nullable = false)
    private boolean wto;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje w środę.
     */
    @Column(name = "sro", nullable = false)
    private boolean sro;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje w czwartek.
     */
    @Column(name = "czw", nullable = false)
    private boolean czw;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje w piątek.
     */
    @Column(name = "pia", nullable = false)
    private boolean pia;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje w sobotę.
     */
    @Column(name = "sob", nullable = false)
    private boolean sob;

    /**
     * Flaga wskazująca, czy harmonogram obowiązuje w niedzielę.
     */
    @Column(name = "nie", nullable = false)
    private boolean nie;

    /**
     * Dodatkowe informacje dotyczące harmonogramu.
     */
    @Column(name = "dodatkowe_inf", length = 200)
    private String dodatkoweInf;

    /**
     * Zwraca identyfikator harmonogramu.
     * @return hamonogramId Identyfikator harmonogramu.
     */
    public Short getHamonogramId() {
        return hamonogramId;
    }

    /**
     * Ustawia identyfikator harmonogramu.
     * @param hamonogramId Identyfikator harmonogramu.
     */
    public void setHamonogramId(Short hamonogramId) {
        this.hamonogramId = hamonogramId;
    }

    /**
     * Zwraca nazwę harmonogramu.
     * @return nazwa Nazwa harmonogramu.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę harmonogramu.
     * @param nazwa Nazwa harmonogramu.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje w poniedziałek.
     * @return pon Flaga dla poniedziałku.
     */
    public boolean isPon() {
        return pon;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje w poniedziałek.
     * @param pon Flaga dla poniedziałku.
     */
    public void setPon(boolean pon) {
        this.pon = pon;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje we wtorek.
     * @return wto Flaga dla wtorku.
     */
    public boolean isWto() {
        return wto;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje we wtorek.
     * @param wto Flaga dla wtorku.
     */
    public void setWto(boolean wto) {
        this.wto = wto;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje w środę.
     * @return sro Flaga dla środy.
     */
    public boolean isSro() {
        return sro;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje w środę.
     * @param sro Flaga dla środy.
     */
    public void setSro(boolean sro) {
        this.sro = sro;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje w czwartek.
     * @return czw Flaga dla czwartku.
     */
    public boolean isCzw() {
        return czw;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje w czwartek.
     * @param czw Flaga dla czwartku.
     */
    public void setCzw(boolean czw) {
        this.czw = czw;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje w piątek.
     * @return pia Flaga dla piątku.
     */
    public boolean isPia() {
        return pia;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje w piątek.
     * @param pia Flaga dla piątku.
     */
    public void setPia(boolean pia) {
        this.pia = pia;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje w sobotę.
     * @return sob Flaga dla soboty.
     */
    public boolean isSob() {
        return sob;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje w sobotę.
     * @param sob Flaga dla soboty.
     */
    public void setSob(boolean sob) {
        this.sob = sob;
    }

    /**
     * Zwraca flagę wskazującą, czy harmonogram obowiązuje w niedzielę.
     * @return nie Flaga dla niedzieli.
     */
    public boolean isNie() {
        return nie;
    }

    /**
     * Ustawia flagę wskazującą, czy harmonogram obowiązuje w niedzielę.
     * @param nie Flaga dla niedzieli.
     */
    public void setNie(boolean nie) {
        this.nie = nie;
    }

    /**
     * Zwraca dodatkowe informacje dotyczące harmonogramu.
     * @return dodatkoweInf Dodatkowe informacje.
     */
    public String getDodatkoweInf() {
        return dodatkoweInf;
    }

    /**
     * Ustawia dodatkowe informacje dotyczące harmonogramu.
     * @param dodatkoweInf Dodatkowe informacje.
     */
    public void setDodatkoweInf(String dodatkoweInf) {
        this.dodatkoweInf = dodatkoweInf;
    }
}
