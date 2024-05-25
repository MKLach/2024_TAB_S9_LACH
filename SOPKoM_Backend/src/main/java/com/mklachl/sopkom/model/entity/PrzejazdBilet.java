package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "przejazdbilet")
public class PrzejazdBilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przejazdbilet_id")
    private Long przejazdBiletId;

    @ManyToOne
    @JoinColumn(name = "przejazd_id", referencedColumnName = "przejazd_id")
    private Przejazd przejazd;

    @ManyToOne
    @JoinColumn(name = "bilet_id", referencedColumnName = "bilet_id")
    private Bilet bilet;

    @Column(name = "cena_biletu", nullable = false)
    private float cenaBiletu;

    // Getters and Setters
    public Long getPrzejazdBiletId() {
        return przejazdBiletId;
    }

    public void setPrzejazdBiletId(Long przejazdBiletId) {
        this.przejazdBiletId = przejazdBiletId;
    }

    public Przejazd getPrzejazd() {
        return przejazd;
    }

    public void setPrzejazd(Przejazd przejazd) {
        this.przejazd = przejazd;
    }

    public Bilet getBilet() {
        return bilet;
    }

    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }

    public float getCenaBiletu() {
        return cenaBiletu;
    }

    public void setCenaBiletu(float cenaBiletu) {
        this.cenaBiletu = cenaBiletu;
    }
}
