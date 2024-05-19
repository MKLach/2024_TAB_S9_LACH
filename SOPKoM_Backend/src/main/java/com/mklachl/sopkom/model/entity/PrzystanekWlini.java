package com.mklachl.sopkom.model.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "przystanekwlini")
public class PrzystanekWlini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przystanekwlini_id")
    private Long przystanekWliniId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "przystanek_id", referencedColumnName = "przystanek_id")
    private Przystanek przystanek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linia_id", referencedColumnName = "linia_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Linia linia;

    @Column(name = "kolejnosc", nullable = false)
    private Short kolejnosc;

    @OneToMany(mappedBy = "przystanekWlini")
    private List<KursPrzystanekWlini> kursPrzystanekWlinii;

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

    public List<KursPrzystanekWlini> getKursPrzystanekWlinii() { return kursPrzystanekWlinii; }

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

    public void setKursPrzystanekWlinii(List<KursPrzystanekWlini> kursPrzystanekWlinii) { this.kursPrzystanekWlinii = kursPrzystanekWlinii; }
}