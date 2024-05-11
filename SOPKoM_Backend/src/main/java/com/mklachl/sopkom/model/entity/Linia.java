package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "linie")
public class Linia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linia_id")
    private Long liniaId;

    @Column(name = "numer", nullable = false, length = 50)
    private String numer;

    @OneToMany(mappedBy = "linia")
    private List<PrzystanekWlini> przystankiWlini;

    // Getters
    public Long getLiniaId() {
        return liniaId;
    }

    public String getNumer() {
        return numer;
    }

    public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }

    // Setters
    public void setLiniaId(Long liniaId) {
        this.liniaId = liniaId;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public void setPrzystankiWlini(List<PrzystanekWlini> przystankiWlini) { this.przystankiWlini = przystankiWlini; }

}
