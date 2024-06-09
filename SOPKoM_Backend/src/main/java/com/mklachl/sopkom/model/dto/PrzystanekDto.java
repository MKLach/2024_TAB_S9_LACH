package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Przystanek;

/**
 * Data Transfer Object (DTO) dla encji Przystanek.
 */
public class PrzystanekDto {

    /**
     * Identyfikator przystanku.
     */
    private Long przystanekId;

    /**
     * Identyfikator odwrotnego przystanku.
     */
    private Long przystanekOdwrotnyId;

    /**
     * Nazwa odwrotnego przystanku.
     */
    private String przystanekOdwrotnyNazwa;

    /**
     * Nazwa przystanku.
     */
    private String nazwa;

    /**
     * Kod pocztowy przystanku.
     */
    private String kodPocztowy;

    /**
     * Miasto, w którym znajduje się przystanek.
     */
    private String miasto;

    /**
     * Ulica, na której znajduje się przystanek.
     */
    private String ulica;

    /**
     * Długość geograficzna przystanku.
     */
    private String dlugoscGeograficzna;

    /**
     * Szerokość geograficzna przystanku.
     */
    private String szerokoscGeograficzna;

    /**
     * Konstruktor domyślny.
     */
    public PrzystanekDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Przystanek.
     * @param przystanek Obiekt encji Przystanek.
     */
    public PrzystanekDto(Przystanek przystanek) {
        this.przystanekId = przystanek.getPrzystanekId();
        
        if (przystanek.getPrzystanekOdwrotny() != null) {
            this.przystanekOdwrotnyId = przystanek.getPrzystanekOdwrotny().getPrzystanekId();
            this.przystanekOdwrotnyNazwa = przystanek.getPrzystanekOdwrotny().getNazwa();
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
     * Zwraca identyfikator odwrotnego przystanku.
     * @return przystanekOdwrotnyId Identyfikator odwrotnego przystanku.
     */
    public Long getPrzystanekOdwrotny() {
        return przystanekOdwrotnyId;
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
     * Ustawia identyfikator odwrotnego przystanku.
     * @param przystanekOdwrotny Identyfikator odwrotnego przystanku.
     */
    public void setPrzystanekOdwrotny(Long przystanekOdwrotny) {
        this.przystanekOdwrotnyId = przystanekOdwrotny;
    }
    
    /**
     * Zwraca nazwę odwrotnego przystanku.
     * @return przystanekOdwrotnyNazwa Nazwa odwrotnego przystanku.
     */
    public String getPrzystanekOdwrotnyNazwa() {
        return przystanekOdwrotnyNazwa;
    }

    /**
     * Ustawia nazwę odwrotnego przystanku.
     * @param przystanekOdwrotnyNazwa Nazwa odwrotnego przystanku.
     */
    public void setPrzystanekOdwrotnyNazwa(String przystanekOdwrotnyNazwa) {
        this.przystanekOdwrotnyNazwa = przystanekOdwrotnyNazwa;
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
