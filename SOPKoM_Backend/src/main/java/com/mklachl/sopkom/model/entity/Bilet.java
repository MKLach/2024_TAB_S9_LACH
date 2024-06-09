package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Klasa reprezentująca encję Bilet.
 */
@Entity
@Table(name = "bilety")
public class Bilet {

    /**
     * Identyfikator biletu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bilet_id")
    private Short biletId;

    /**
     * Cena biletu.
     */
    @Column(name = "cena", nullable = false)
    private float cena;

    /**
     * Nazwa biletu.
     */
    @Column(name = "nazwa", nullable = false, length = 30)
    private String nazwa;

    /**
     * Lista przejazdów związanych z biletem.
     */
    @OneToMany(mappedBy = "bilet")
    private List<PrzejazdBilet> PrzejazdBilet;

    // Getters and Setters

    /**
     * Zwraca identyfikator biletu.
     * @return biletId Identyfikator biletu.
     */
    public Short getBiletId() {
        return biletId;
    }

    /**
     * Ustawia identyfikator biletu.
     * @param biletId Identyfikator biletu.
     */
    public void setBiletId(Short biletId) {
        this.biletId = biletId;
    }

    /**
     * Zwraca cenę biletu.
     * @return cena Cena biletu.
     */
    public float getCena() {
        return cena;
    }

    /**
     * Ustawia cenę biletu.
     * @param cena Cena biletu.
     */
    public void setCena(float cena) {
        this.cena = cena;
    }

    /**
     * Zwraca nazwę biletu.
     * @return nazwa Nazwa biletu.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę biletu.
     * @param nazwa Nazwa biletu.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca listę przejazdów związanych z biletem.
     * @return PrzejazdBilet Lista przejazdów.
     */
    public List<PrzejazdBilet> getPrzejazdBilet() {
        return PrzejazdBilet;
    }

    /**
     * Ustawia listę przejazdów związanych z biletem.
     * @param PrzejazdBilet Lista przejazdów.
     */
    public void setPrzejazdBilet(List<PrzejazdBilet> PrzejazdBilet) {
        this.PrzejazdBilet = PrzejazdBilet;
    }
}
