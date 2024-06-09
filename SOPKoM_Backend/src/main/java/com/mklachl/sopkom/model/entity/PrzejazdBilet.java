package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;

/**
 * Klasa reprezentująca encję PrzejazdBilet.
 */
@Entity
@Table(name = "przejazdbilet")
public class PrzejazdBilet {

    /**
     * Identyfikator PrzejazdBilet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przejazdbilet_id")
    private Long przejazdBiletId;

    /**
     * Przejazd związany z biletem.
     */
    @ManyToOne
    @JoinColumn(name = "przejazd_id", referencedColumnName = "przejazd_id")
    private Przejazd przejazd;

    /**
     * Bilet związany z przejazdem.
     */
    @ManyToOne
    @JoinColumn(name = "bilet_id", referencedColumnName = "bilet_id")
    private Bilet bilet;

    /**
     * Cena biletu.
     */
    @Column(name = "cena_biletu", nullable = false)
    private float cenaBiletu;

    /**
     * Zwraca identyfikator PrzejazdBilet.
     * @return przejazdBiletId Identyfikator PrzejazdBilet.
     */
    public Long getPrzejazdBiletId() {
        return przejazdBiletId;
    }

    /**
     * Ustawia identyfikator PrzejazdBilet.
     * @param przejazdBiletId Identyfikator PrzejazdBilet.
     */
    public void setPrzejazdBiletId(Long przejazdBiletId) {
        this.przejazdBiletId = przejazdBiletId;
    }

    /**
     * Zwraca przejazd związany z biletem.
     * @return przejazd Przejazd związany z biletem.
     */
    public Przejazd getPrzejazd() {
        return przejazd;
    }

    /**
     * Ustawia przejazd związany z biletem.
     * @param przejazd Przejazd związany z biletem.
     */
    public void setPrzejazd(Przejazd przejazd) {
        this.przejazd = przejazd;
    }

    /**
     * Zwraca bilet związany z przejazdem.
     * @return bilet Bilet związany z przejazdem.
     */
    public Bilet getBilet() {
        return bilet;
    }

    /**
     * Ustawia bilet związany z przejazdem.
     * @param bilet Bilet związany z przejazdem.
     */
    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }

    /**
     * Zwraca cenę biletu.
     * @return cenaBiletu Cena biletu.
     */
    public float getCenaBiletu() {
        return cenaBiletu;
    }

    /**
     * Ustawia cenę biletu.
     * @param cenaBiletu Cena biletu.
     */
    public void setCenaBiletu(float cenaBiletu) {
        this.cenaBiletu = cenaBiletu;
    }
}
