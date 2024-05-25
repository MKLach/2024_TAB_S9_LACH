package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bilety")
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bilet_id")
    private Short biletId;

    @Column(name = "cena", nullable = false)
    private float cena;

    @Column(name = "nazwa", nullable = false, length = 30)
    private String nazwa;

    @OneToMany(mappedBy = "bilet")
    private List<PrzejazdBilet> PrzejazdBilet;

    // Getters and Setters
    public Short getBiletId() {
        return biletId;
    }

    public void setBiletId(Short biletId) {
        this.biletId = biletId;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<PrzejazdBilet> getPrzejazdBilet() {
        return PrzejazdBilet;
    }

    public void setPrzejazdBilet(List<PrzejazdBilet> PrzejazdBilet) {
        this.PrzejazdBilet = PrzejazdBilet;
    }
}
