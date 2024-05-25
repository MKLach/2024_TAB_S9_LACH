package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "bilety")
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bilet_id")
    private Long biletId;

    @Column(name = "cena", nullable = false)
    private float cena;

    @Column(name = "nazwa", nullable = false, length = 30)
    private String nazwa;

    // Getters and Setters
    public Long getBiletId() {
        return biletId;
    }

    public void setBiletId(Long biletId) {
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
}
