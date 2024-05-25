package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "data_startu", nullable = true)
    private Date dataStartu;

    @Column(name = "data_konca", nullable = true)
    private Date dataKonca;

    @OneToMany(mappedBy = "przejazd")
    private List<PrzejazdKursPrzystanekWlini> przejazdKursPrzystanekWlini;


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

    public Date getDataStartu() {
        return dataStartu;
    }

    public Date getDataKonca() {
        return dataKonca;
    }

    public List<PrzejazdKursPrzystanekWlini> getPrzejazdKursPrzystanekWlini() {
        return przejazdKursPrzystanekWlini;
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

    public void setDataStartu(Date dataStartu) {
        this.dataStartu = dataStartu;
    }

    public void setDataKonca(Date dataKonca) {
        this.dataKonca = dataKonca;
    }

    public void setPrzejazdKursPrzystanekWlini(List<PrzejazdKursPrzystanekWlini> przejazdKursPrzystanekWlini) {
        this.przejazdKursPrzystanekWlini = przejazdKursPrzystanekWlini;
    }
}