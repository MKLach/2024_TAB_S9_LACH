package com.mklachl.sopkom.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "linie")
public class Linia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linia_id")
    private Long liniaId;

    @Column(name = "numer", nullable = false, length = 50)
    private String numer;

    // Getters
    public Long getLiniaId() {
        return liniaId;
    }

    public String getNumer() {
        return numer;
    }

    // Setters
    public void setLiniaId(Long liniaId) {
        this.liniaId = liniaId;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

}
