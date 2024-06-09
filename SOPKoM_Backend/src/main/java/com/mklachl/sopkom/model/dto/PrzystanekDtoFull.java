package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Przystanek;

/**
 * Data Transfer Object (DTO) dla encji Przystanek z pełnymi informacjami.
 */
public class PrzystanekDtoFull {

    /**
     * Identyfikator przystanku.
     */
    protected Long przystanekId;

    /**
     * DTO odwrotnego przystanku.
     */
    protected PrzystanekDtoFull przystanekOdwrotny;

    /**
     * Nazwa przystanku.
     */
    protected String nazwa;

    /**
     * Kod pocztowy przystanku.
     */
    protected String kodPocztowy;

    /**
     * Miasto, w którym znajduje się przystanek.
     */
    protected String miasto;

    /**
     * Ulica, na której znajduje się przystanek.
     */
    protected String ulica;

    /**
     * Długość geograficzna przystanku.
     */
    protected String dlugoscGeograficzna;

    /**
     * Szerokość geograficzna przystanku.
     */
    protected String szerokoscGeograficzna;

    /**
     * Konstruktor domyślny.
     */
    public PrzystanekDtoFull() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Przystanek.
     * @param przystanek Obiekt encji Przystanek.
     */
    public PrzystanekDtoFull(Przystanek przystanek) {
        this.przystanekId = przystanek.getPrzystanekId();
        
        if (przystanek.getPrzystanekOdwrotny() != null) {
            this.przystanekOdwrotny = new PrzystanekDtoFull(przystanek.getPrzystanekOdwrotny());  
        }
        
        this.nazwa = przystanek.getNazwa();
        this.kodPocztowy = przystanek.getKodPocztowy();
        this.miasto = przystanek.getMiasto();
        this.ulica = przystanek.getUlica();
        this.dlugoscGeograficzna = przystanek.getDlugoscGeograficzna();
        this.szerokoscGeograficzna = przystanek.getSzerokoscGeograficzna();
    }

    // Getters

    /**
     * Zwraca identyfikator przystanku.
     * @return przystanekId Identyfikator przystanku.
     */
    public Long getPrzystanekId() {
        return przystanekId;
    }

    /**
     * Zwraca DTO odwrotnego przystanku.
     * @return przystanekOdwrotny DTO odwrotnego przystanku.
     */
    public PrzystanekDtoFull getPrzystanekOdwrotny() {
        return przystanekOdwrotny;
    }

    /**
     * Zwraca nazwę przystanku.
     * @return nazwa Nazwa przystanku.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Zwraca kod pocztowy przystanku.
     * @return kodPocztowy Kod pocztowy przystanku.
     */
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    /**
     * Zwraca miasto, w którym znajduje się przystanek.
     * @return miasto Miasto przystanku.
     */
    public String getMiasto() {
        return miasto;
    }

    /**
     * Zwraca ulicę, na której znajduje się przystanek.
     * @return ulica Ulica przystanku.
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * Zwraca długość geograficzną przystanku.
     * @return dlugoscGeograficzna Długość geograficzna przystanku.
     */
    public String getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    /**
     * Zwraca szerokość geograficzną przystanku.
     * @return szerokoscGeograficzna Szerokość geograficzna przystanku.
     */
    public String getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    // Setters

    /**
     * Ustawia identyfikator przystanku.
     * @param przystanekId Identyfikator przystanku.
     */
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

    /**
     * Ustawia DTO odwrotnego przystanku.
     * @param przystanekOdwrotny DTO odwrotnego przystanku.
     */
    public void setPrzystanekOdwrotny(PrzystanekDtoFull przystanekOdwrotny) {
        this.przystanekOdwrotny = przystanekOdwrotny;
    }

    /**
     * Ustawia nazwę przystanku.
     * @param nazwa Nazwa przystanku.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Ustawia kod pocztowy przystanku.
     * @param kodPocztowy Kod pocztowy przystanku.
     */
    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    /**
     * Ustawia miasto, w którym znajduje się przystanek.
     * @param miasto Miasto przystanku.
     */
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    /**
     * Ustawia ulicę, na której znajduje się przystanek.
     * @param ulica Ulica przystanku.
     */
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    /**
     * Ustawia długość geograficzną przystanku.
     * @param dlugoscGeograficzna Długość geograficzna przystanku.
     */
    public void setDlugoscGeograficzna(String dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    /**
     * Ustawia szerokość geograficzną przystanku.
     * @param szerokoscGeograficzna Szerokość geograficzna przystanku.
     */
    public void setSzerokoscGeograficzna(String szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }
}
