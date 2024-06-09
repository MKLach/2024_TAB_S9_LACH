package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Klasa reprezentująca encję Kurs.
 */
@Entity
@Table(name = "kursy")
public class Kurs {

    /**
     * Identyfikator kursu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kurs_id")
    private Long kursId;

    /**
     * Linia związana z kursem.
     */
    @ManyToOne
    @JoinColumn(name = "linia_id", referencedColumnName = "linia_id")
    private Linia linia;

    /**
     * Harmonogram związany z kursem.
     */
    @ManyToOne
    @JoinColumn(name = "harmonogram_id", referencedColumnName = "hamonogram_id")
    private Harmonogram harmonogram;

    /**
     * Kierunek kursu.
     */
    @Column(name = "kierunek", nullable = false)
    private Short kierunek;

    /**
     * Typ autobusu używanego na kursie.
     */
    @Column(name = "typ_autobusu", nullable = false)
    private Short typAutobusu;

    /**
     * Lista przystanków w linii związanych z kursem.
     */
    @OneToMany(mappedBy = "kurs")
    private List<KursPrzystanekWlini> kursPrzystanekWlinii;

    /**
     * Zwraca identyfikator kursu.
     * @return kursId Identyfikator kursu.
     */
    public Long getKursId() {
        return kursId;
    }

    /**
     * Ustawia identyfikator kursu.
     * @param kursId Identyfikator kursu.
     */
    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    /**
     * Zwraca linię związaną z kursem.
     * @return linia Linia związana z kursem.
     */
    public Linia getLinia() {
        return linia;
    }

    /**
     * Ustawia linię związaną z kursem.
     * @param linia Linia związana z kursem.
     */
    public void setLinia(Linia linia) {
        this.linia = linia;
    }

    /**
     * Zwraca harmonogram związany z kursem.
     * @return harmonogram Harmonogram związany z kursem.
     */
    public Harmonogram getHarmonogram() {
        return harmonogram;
    }

    /**
     * Ustawia harmonogram związany z kursem.
     * @param harmonogram Harmonogram związany z kursem.
     */
    public void setHarmonogram(Harmonogram harmonogram) {
        this.harmonogram = harmonogram;
    }

    /**
     * Zwraca kierunek kursu.
     * @return kierunek Kierunek kursu.
     */
    public Short getKierunek() {
        return kierunek;
    }

    /**
     * Ustawia kierunek kursu.
     * @param kierunek Kierunek kursu.
     */
    public void setKierunek(Short kierunek) {
        this.kierunek = kierunek;
    }

    /**
     * Zwraca typ autobusu używanego na kursie.
     * @return typAutobusu Typ autobusu.
     */
    public Short getTypAutobusu() {
        return typAutobusu;
    }

    /**
     * Ustawia typ autobusu używanego na kursie.
     * @param typAutobusu Typ autobusu.
     */
    public void setTypAutobusu(Short typAutobusu) {
        this.typAutobusu = typAutobusu;
    }

    /**
     * Zwraca listę przystanków w linii związanych z kursem.
     * @return kursPrzystanekWlinii Lista przystanków w linii.
     */
    public List<KursPrzystanekWlini> getKursPrzystanekWlinii() {
        return kursPrzystanekWlinii;
    }

    /**
     * Ustawia listę przystanków w linii związanych z kursem.
     * @param kursPrzystanekWlinii Lista przystanków w linii.
     */
    public void setKursPrzystanekWlinii(List<KursPrzystanekWlini> kursPrzystanekWlinii) {
        this.kursPrzystanekWlinii = kursPrzystanekWlinii;
    }
}
