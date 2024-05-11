package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "przystanki")
public class Przystanek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przystanek_id")
    private Long przystanekId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "przystanek_odwrotny_id", referencedColumnName = "przystanek_id")
    private Przystanek przystanekOdwrotny;

    @Column(name = "nazwa", nullable = false, length = 50)
    private String nazwa;

    @Column(name = "kod_pocztowy", nullable = false, length = 10)
    private String kodPocztowy;

    @Column(name = "miasto", nullable = false, length = 50)
    private String miasto;

    @Column(name = "ulica", nullable = false, length = 50)
    private String ulica;

    @Column(name = "dlugosc_geograficzna", nullable = false, length = 20)
    private String dlugoscGeograficzna;

    @Column(name = "szerokosc_geograficzna", nullable = false, length = 20)
    private String szerokoscGeograficzna;

    @OneToMany(mappedBy = "przystanek")
    private List<PrzystanekWlini> przystankiWlini;

    // Getters
    public Long getPrzystanekId() {
        return przystanekId;
    }

    public Przystanek getPrzystanekOdwrotny() {
        return przystanekOdwrotny;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public String getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    public String getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }

    // Setters
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

    public void setPrzystanekOdwrotny(Przystanek przystanekOdwrotny) {
        this.przystanekOdwrotny = przystanekOdwrotny;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setDlugoscGeograficzna(String dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public void setSzerokoscGeograficzna(String szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }

    public void setPrzystankiWlini(List<PrzystanekWlini> przystankiWlini) {
        this.przystankiWlini = przystankiWlini;
    }
}