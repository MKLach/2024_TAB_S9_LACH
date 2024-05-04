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
@Table(name = "kursy")
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kurs_id")
    private Long kursId;

    @ManyToOne
    @JoinColumn(name = "linia_id", referencedColumnName = "linia_id")
    private Linia linia;

    @ManyToOne
    @JoinColumn(name = "harmonogram_id", referencedColumnName = "hamonogram_id")
    private Harmonogram harmonogram;

    @Column(name = "kierunek", nullable = false)
    private Short kierunek;

    @Column(name = "typ_autobusu", nullable = false)
    private Short typAutobusu;

    // Getters
    public Long getKursId() {
        return kursId;
    }

    public Linia getLinia() {
        return linia;
    }

    public Harmonogram getHarmonogram() {
        return harmonogram;
    }

    public Short getKierunek() {
        return kierunek;
    }

    public Short getTypAutobusu() {
        return typAutobusu;
    }

    // Setters
    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public void setLinia(Linia linia) {
        this.linia = linia;
    }

    public void setHarmonogram(Harmonogram harmonogram) {
        this.harmonogram = harmonogram;
    }

    public void setKierunek(Short kierunek) {
        this.kierunek = kierunek;
    }

    public void setTypAutobusu(Short typAutobusu) {
        this.typAutobusu = typAutobusu;
    }
}
