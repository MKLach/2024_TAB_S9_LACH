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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "kierowcy")
public class Kierowca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kierowca_id")
    private Long kierowcaId;

    @Column(name = "prawojazdywaznedo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prawoJazdyWazneDo;

    @Column(name = "imie", nullable = false, length = 50)
    private String imie;

    @Column(name = "nazwisko", nullable = false, length = 50)
    private String nazwisko;

    @Column(name = "pesel", nullable = false, length = 11)
    private String pesel;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Getters
    public Long getKierowcaId() {
        return kierowcaId;
    }

    public Date getPrawoJazdyWazneDo() {
        return prawoJazdyWazneDo;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    public void setPrawoJazdyWazneDo(Date prawoJazdyWazneDo) {
        this.prawoJazdyWazneDo = prawoJazdyWazneDo;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setUser(User user) {
        this.user = user;
    }
}