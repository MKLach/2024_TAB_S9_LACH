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
@Table(name = "przystanekwlini")
public class PrzystanekWlini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przystanekwlini_id")
    private Long przystanekWliniId;

    @ManyToOne
    @JoinColumn(name = "przystanek_id", referencedColumnName = "przystanek_id")
    private Przystanek przystanek;

    @ManyToOne
    @JoinColumn(name = "linia_id", referencedColumnName = "linia_id")
    private Linia linia;

    @Column(name = "kolejnosc", nullable = false)
    private Short kolejnosc;

    // Getters
    public Long getPrzystanekWliniId() {
        return przystanekWliniId;
    }

    public Przystanek getPrzystanek() {
        return przystanek;
    }

    public Linia getLinia() {
        return linia;
    }

    public Short getKolejnosc() {
        return kolejnosc;
    }

    // Setters
    public void setPrzystanekWliniId(Long przystanekWliniId) {
        this.przystanekWliniId = przystanekWliniId;
    }

    public void setPrzystanek(Przystanek przystanek) {
        this.przystanek = przystanek;
    }

    public void setLinia(Linia linia) {
        this.linia = linia;
    }

    public void setKolejnosc(Short kolejnosc) {
        this.kolejnosc = kolejnosc;
    }
}