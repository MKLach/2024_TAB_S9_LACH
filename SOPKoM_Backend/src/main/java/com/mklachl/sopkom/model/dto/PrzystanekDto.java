package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Przystanek;

public class PrzystanekDto {
    private Long przystanekId;

    private Przystanek przystanekOdwrotny;

    private String nazwa;

    private String kodPocztowy;

    private String miasto;

    private String ulica;

    private String dlugoscGeograficzna;

    private String szerokoscGeograficzna;

    PrzystanekDto(){

    }

    public PrzystanekDto(Przystanek przystanek){
        this.przystanekId = przystanek.getPrzystanekId();
        this.przystanekOdwrotny = przystanek.getPrzystanekOdwrotny();
        this.nazwa = przystanek.getNazwa();
        this.kodPocztowy = przystanek.getKodPocztowy();
        this.miasto = przystanek.getMiasto();
        this.ulica = przystanek.getUlica();
        this.dlugoscGeograficzna = przystanek.getDlugoscGeograficzna();
        this.szerokoscGeograficzna = przystanek.getSzerokoscGeograficzna();
    }

    // Getters
    public Long getPrzystanekId() {
        return przystanekId;
    }

    public Przystanek getPrzystanekOdwrotny() {
        return przystanekOdwrotny;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public String getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    public String getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    /*public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }*/

    // Setters
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

    public void setPrzystanekOdwrotny(Przystanek przystanekOdwrotny) {
        this.przystanekOdwrotny = przystanekOdwrotny;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setDlugoscGeograficzna(String dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public void setSzerokoscGeograficzna(String szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }
}
