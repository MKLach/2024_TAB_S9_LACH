package com.mklachl.sopkom.model.dto.harmonogram;

import com.mklachl.sopkom.model.entity.Harmonogram;

public class HarmonogramDto {
    
    private short harmonogramId;
    private String nazwa;
    private boolean pon;
    private boolean wto;
    private boolean sro;
    private boolean czw;
    private boolean pia;
    private boolean sob;
    private boolean nie;
    private String dodatkoweInf;

    public HarmonogramDto() {

    }

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
    public short getHarmonogramId() {
        return harmonogramId;
    }

    public void setHarmonogramId(short harmonogramId) {
        this.harmonogramId = harmonogramId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public boolean isPon() {
        return pon;
    }

    public void setPon(boolean pon) {
        this.pon = pon;
    }

    public boolean isWto() {
        return wto;
    }

    public void setWto(boolean wto) {
        this.wto = wto;
    }

    public boolean isSro() {
        return sro;
    }

    public void setSro(boolean sro) {
        this.sro = sro;
    }

    public boolean isCzw() {
        return czw;
    }

    public void setCzw(boolean czw) {
        this.czw = czw;
    }

    public boolean isPia() {
        return pia;
    }

    public void setPia(boolean pia) {
        this.pia = pia;
    }

    public boolean isSob() {
        return sob;
    }

    public void setSob(boolean sob) {
        this.sob = sob;
    }

    public boolean isNie() {
        return nie;
    }

    public void setNie(boolean nie) {
        this.nie = nie;
    }

    public String getDodatkoweInf() {
        return dodatkoweInf;
    }

    public void setDodatkoweInf(String dodatkoweInf) {
        this.dodatkoweInf = dodatkoweInf;
    }
}
