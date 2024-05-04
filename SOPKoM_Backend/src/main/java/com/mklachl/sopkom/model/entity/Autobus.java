package com.mklachl.sopkom.model.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
@Entity
@Table(name = "autobusy")
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autbous_id")
    private Long autbousId;

    @Column(name = "numer_rejestracyjny", nullable = false, length = 10)
    private String numerRejestracyjny;

    @Column(name = "przegladwaznydo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date przegladWaznyDo;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "przebieg", nullable = false)
    private float przebieg;

    // Getters
    public Long getAutbousId() {
        return autbousId;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public Date getPrzegladWaznyDo() {
        return przegladWaznyDo;
    }

    public String getStatus() {
        return status;
    }

    public float getPrzebieg() {
        return przebieg;
    }

    // Setters
    public void setAutbousId(Long autbousId) {
        this.autbousId = autbousId;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public void setPrzegladWaznyDo(Date przegladWaznyDo) {
        this.przegladWaznyDo = przegladWaznyDo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrzebieg(float przebieg) {
        this.przebieg = przebieg;
    }
}