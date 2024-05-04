package com.mklachl.sopkom.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "przejazdy")
public class Przejazd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przejazd_id")
    private Long przejazdId;

    @ManyToOne
    @JoinColumn(name = "kierowca_id", referencedColumnName = "kierowca_id")
    private Kierowca kierowca;

    @ManyToOne
    @JoinColumn(name = "autobus_id", referencedColumnName = "autbous_id")
    private Autobus autobus;

    @ManyToOne
    @JoinColumn(name = "kurs_id", referencedColumnName = "kurs_id")
    private Kurs kurs;

    @Column(name = "spalanie", nullable = false)
    private float spalanie;

    @Column(name = "cena_za_litr", nullable = false)
    private float cenaZaLitr;

    @Column(name = "dlugosc_trasy", nullable = false)
    private float dlugoscTrasy;

    // Getters
    public Long getPrzejazdId() {
        return przejazdId;
    }

    public Kierowca getKierowca() {
        return kierowca;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public float getCenaZaLitr() {
        return cenaZaLitr;
    }

    public float getDlugoscTrasy() {
        return dlugoscTrasy;
    }

    // Setters
    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    public void setKierowca(Kierowca kierowca) {
        this.kierowca = kierowca;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    public void setCenaZaLitr(float cenaZaLitr) {
        this.cenaZaLitr = cenaZaLitr;
    }

    public void setDlugoscTrasy(float dlugoscTrasy) {
        this.dlugoscTrasy = dlugoscTrasy;
    }
}