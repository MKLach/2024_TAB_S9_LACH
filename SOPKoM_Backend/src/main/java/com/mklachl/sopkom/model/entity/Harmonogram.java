package com.mklachl.sopkom.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "harmonogramy")
public class Harmonogram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hamonogram_id")
    private Short hamonogramId;

    @Column(name = "nazwa", length = 50)
    private String nazwa;

    @Column(name = "pon", nullable = false)
    private boolean pon;

    @Column(name = "wto", nullable = false)
    private boolean wto;

    @Column(name = "sro", nullable = false)
    private boolean sro;

    @Column(name = "czw", nullable = false)
    private boolean czw;

    @Column(name = "pia", nullable = false)
    private boolean pia;

    @Column(name = "sob", nullable = false)
    private boolean sob;

    @Column(name = "nie", nullable = false)
    private boolean nie;

    @Column(name = "dodatkowe_inf", length = 200)
    private String dodatkoweInf;

    // Getters
    public Short getHamonogramId() {
        return hamonogramId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public boolean isPon() {
        return pon;
    }

    public boolean isWto() {
        return wto;
    }

    public boolean isSro() {
        return sro;
    }

    public boolean isCzw() {
        return czw;
    }

    public boolean isPia() {
        return pia;
    }

    public boolean isSob() {
        return sob;
    }

    public boolean isNie() {
        return nie;
    }

    public String getDodatkoweInf() {
        return dodatkoweInf;
    }

    // Setters
    public void setHamonogramId(Short hamonogramId) {
        this.hamonogramId = hamonogramId;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setPon(boolean pon) {
        this.pon = pon;
    }

    public void setWto(boolean wto) {
        this.wto = wto;
    }

    public void setSro(boolean sro) {
        this.sro = sro;
    }

    public void setCzw(boolean czw) {
        this.czw = czw;
    }

    public void setPia(boolean pia) {
        this.pia = pia;
    }

    public void setSob(boolean sob) {
        this.sob = sob;
    }

    public void setNie(boolean nie) {
        this.nie = nie;
    }

    public void setDodatkoweInf(String dodatkoweInf) {
        this.dodatkoweInf = dodatkoweInf;
    }
    
    
    
}