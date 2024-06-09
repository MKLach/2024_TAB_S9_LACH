package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Klasa reprezentująca encję Przystanek.
 */
@Entity
@Table(name = "przystanki")
public class Przystanek {

    /**
     * Identyfikator przystanku.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przystanek_id")
    private Long przystanekId;

    /**
     * Przystanek odwrotny.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "przystanek_odwrotny_id", referencedColumnName = "przystanek_id")
    private Przystanek przystanekOdwrotny;

    /**
     * Nazwa przystanku.
     */
    @Column(name = "nazwa", nullable = false, length = 50)
    private String nazwa;

    /**
     * Kod pocztowy przystanku.
     */
    @Column(name = "kod_pocztowy", nullable = false, length = 10)
    private String kodPocztowy;

    /**
     * Miasto, w którym znajduje się przystanek.
     */
    @Column(name = "miasto", nullable = false, length = 50)
    private String miasto;

    /**
     * Ulica, na której znajduje się przystanek.
     */
    @Column(name = "ulica", nullable = false, length = 50)
    private String ulica;

    /**
     * Długość geograficzna przystanku.
     */
    @Column(name = "dlugosc_geograficzna", nullable = false, length = 20)
    private String dlugoscGeograficzna;

    /**
     * Szerokość geograficzna przystanku.
     */
    @Column(name = "szerokosc_geograficzna", nullable = false, length = 20)
    private String szerokoscGeograficzna;

    /**
     * Lista przystanków w linii związanych z przystankiem.
     */
    @OneToMany(mappedBy = "przystanek")
    private List<PrzystanekWlini> przystankiWlini;

    // Getters

    /**
     * Zwraca identyfikator przystanku.
     * @return przystanekId Identyfikator przystanku.
     */
    public Long getPrzystanekId() {
        return przystanekId;
    }

    /**
     * Zwraca przystanek odwrotny.
     * @return przystanekOdwrotny Przystanek odwrotny.
     */
    public Przystanek getPrzystanekOdwrotny() {
        return przystanekOdwrotny;
    }

    /**
     * Zwraca nazwę przystanku.
     * @return nazwa Nazwa przystanku.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Zwraca kod pocztowy przystanku.
     * @return kodPocztowy Kod pocztowy przystanku.
     */
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    /**
     * Zwraca miasto, w którym znajduje się przystanek.
     * @return miasto Miasto przystanku.
     */
    public String getMiasto() {
        return miasto;
    }

    /**
     * Zwraca ulicę, na której znajduje się przystanek.
     * @return ulica Ulica przystanku.
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * Zwraca długość geograficzną przystanku.
     * @return dlugoscGeograficzna Długość geograficzna przystanku.
     */
    public String getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    /**
     * Zwraca szerokość geograficzną przystanku.
     * @return szerokoscGeograficzna Szerokość geograficzna przystanku.
     */
    public String getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    /**
     * Zwraca listę przystanków w linii związanych z przystankiem.
     * @return przystankiWlini Lista przystanków w linii.
     */
    public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }

    // Setters

    /**
     * Ustawia identyfikator przystanku.
     * @param przystanekId Identyfikator przystanku.
     */
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

    /**
     * Ustawia przystanek odwrotny.
     * @param przystanekOdwrotny Przystanek odwrotny.
     */
    public void setPrzystanekOdwrotny(Przystanek przystanekOdwrotny) {
        this.przystanekOdwrotny = przystanekOdwrotny;
    }

    /**
     * Ustawia nazwę przystanku.
     * @param nazwa Nazwa przystanku.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Ustawia kod pocztowy przystanku.
     * @param kodPocztowy Kod pocztowy przystanku.
     */
    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    /**
     * Ustawia miasto, w którym znajduje się przystanek.
     * @param miasto Miasto przystanku.
     */
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    /**
     * Ustawia ulicę, na której znajduje się przystanek.
     * @param ulica Ulica przystanku.
     */
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    /**
     * Ustawia długość geograficzną przystanku.
     * @param dlugoscGeograficzna Długość geograficzna przystanku.
     */
    public void setDlugoscGeograficzna(String dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    /**
     * Ustawia szerokość geograficzną przystanku.
     * @param szerokoscGeograficzna Szerokość geograficzna przystanku.
     */
    public void setSzerokoscGeograficzna(String szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }

    /**
     * Ustawia listę przystanków w linii związanych z przystankiem.
     * @param przystankiWlini Lista przystanków w linii.
     */
    public void setPrzystankiWlini(List<PrzystanekWlini> przystankiWlini) {
        this.przystankiWlini = przystankiWlini;
    }
}
