package com.mklachl.sopkom.model.dto.harmonogram;

import com.mklachl.sopkom.model.entity.Harmonogram;

/**
 * Data Transfer Object (DTO) dla encji Harmonogram.
 */
public class HarmonogramDto {

    /**
     * Identyfikator harmonogramu.
     */
    private short harmonogramId;

    /**
     * Nazwa harmonogramu.
     */
    private String nazwa;

    /**
     * Czy harmonogram dotyczy poniedziałku.
     */
    private boolean pon;

    /**
     * Czy harmonogram dotyczy wtorku.
     */
    private boolean wto;

    /**
     * Czy harmonogram dotyczy środy.
     */
    private boolean sro;

    /**
     * Czy harmonogram dotyczy czwartku.
     */
    private boolean czw;

    /**
     * Czy harmonogram dotyczy piątku.
     */
    private boolean pia;

    /**
     * Czy harmonogram dotyczy soboty.
     */
    private boolean sob;

    /**
     * Czy harmonogram dotyczy niedzieli.
     */
    private boolean nie;

    /**
     * Dodatkowe informacje dotyczące harmonogramu.
     */
    private String dodatkoweInf;

    /**
     * Konstruktor domyślny.
     */
    public HarmonogramDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Harmonogram.
     * @param harmonogram Obiekt encji Harmonogram.
     */
    public HarmonogramDto(Harmonogram harmonogram) {
        this.harmonogramId = harmonogram.getHamonogramId();
        this.nazwa = harmonogram.getNazwa();
        this.pon = harmonogram.isPon();
        this.wto = harmonogram.isWto();
        this.sro = harmonogram.isSro();
        this.czw = harmonogram.isCzw();
        this.pia = harmonogram.isPia();
        this.sob = harmonogram.isSob();
        this.nie = harmonogram.isNie();
        this.dodatkoweInf = harmonogram.getDodatkoweInf();
    }

    // Getters and Setters

    /**
     * Zwraca identyfikator harmonogramu.
     * @return harmonogramId Identyfikator harmonogramu.
     */
    public short getHarmonogramId() {
        return harmonogramId;
    }

    /**
     * Ustawia identyfikator harmonogramu.
     * @param harmonogramId Identyfikator harmonogramu.
     */
    public void setHarmonogramId(short harmonogramId) {
        this.harmonogramId = harmonogramId;
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
     * Zwraca wartość, czy harmonogram dotyczy poniedziałku.
     * @return pon Wartość czy harmonogram dotyczy poniedziałku.
     */
    public boolean isPon() {
        return pon;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy poniedziałku.
     * @param pon Wartość czy harmonogram dotyczy poniedziałku.
     */
    public void setPon(boolean pon) {
        this.pon = pon;
    }

    /**
     * Zwraca wartość, czy harmonogram dotyczy wtorku.
     * @return wto Wartość czy harmonogram dotyczy wtorku.
     */
    public boolean isWto() {
        return wto;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy wtorku.
     * @param wto Wartość czy harmonogram dotyczy wtorku.
     */
    public void setWto(boolean wto) {
        this.wto = wto;
    }

    /**
     * Zwraca wartość, czy harmonogram dotyczy środy.
     * @return sro Wartość czy harmonogram dotyczy środy.
     */
    public boolean isSro() {
        return sro;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy środy.
     * @param sro Wartość czy harmonogram dotyczy środy.
     */
    public void setSro(boolean sro) {
        this.sro = sro;
    }

    /**
     * Zwraca wartość, czy harmonogram dotyczy czwartku.
     * @return czw Wartość czy harmonogram dotyczy czwartku.
     */
    public boolean isCzw() {
        return czw;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy czwartku.
     * @param czw Wartość czy harmonogram dotyczy czwartku.
     */
    public void setCzw(boolean czw) {
        this.czw = czw;
    }

    /**
     * Zwraca wartość, czy harmonogram dotyczy piątku.
     * @return pia Wartość czy harmonogram dotyczy piątku.
     */
    public boolean isPia() {
        return pia;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy piątku.
     * @param pia Wartość czy harmonogram dotyczy piątku.
     */
    public void setPia(boolean pia) {
        this.pia = pia;
    }

    /**
     * Zwraca wartość, czy harmonogram dotyczy soboty.
     * @return sob Wartość czy harmonogram dotyczy soboty.
     */
    public boolean isSob() {
        return sob;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy soboty.
     * @param sob Wartość czy harmonogram dotyczy soboty.
     */
    public void setSob(boolean sob) {
        this.sob = sob;
    }

    /**
     * Zwraca wartość, czy harmonogram dotyczy niedzieli.
     * @return nie Wartość czy harmonogram dotyczy niedzieli.
     */
    public boolean isNie() {
        return nie;
    }

    /**
     * Ustawia wartość, czy harmonogram dotyczy niedzieli.
     * @param nie Wartość czy harmonogram dotyczy niedzieli.
     */
    public void setNie(boolean nie) {
        this.nie = nie;
    }

    /**
     * Zwraca dodatkowe informacje dotyczące harmonogramu.
     * @return dodatkoweInf Dodatkowe informacje dotyczące harmonogramu.
     */
    public String getDodatkoweInf() {
        return dodatkoweInf;
    }

    /**
     * Ustawia dodatkowe informacje dotyczące harmonogramu.
     * @param dodatkoweInf Dodatkowe informacje dotyczące harmonogramu.
     */
    public void setDodatkoweInf(String dodatkoweInf) {
        this.dodatkoweInf = dodatkoweInf;
    }
}
