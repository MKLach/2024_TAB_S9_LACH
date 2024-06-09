package com.mklachl.sopkom.model.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import java.util.List;

/**
 * Klasa reprezentująca encję PrzystanekWlini.
 */
@Entity
@Table(name = "przystanekwlini")
public class PrzystanekWlini {

    /**
     * Identyfikator PrzystanekWlini.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przystanekwlini_id")
    private Long przystanekWliniId;

    /**
     * Przystanek związany z PrzystanekWlini.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "przystanek_id", referencedColumnName = "przystanek_id")
    private Przystanek przystanek;

    /**
     * Linia związana z PrzystanekWlini.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linia_id", referencedColumnName = "linia_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Linia linia;

    /**
     * Kolejność przystanku w linii.
     */
    @Column(name = "kolejnosc", nullable = false)
    private Short kolejnosc;

    /**
     * Lista KursPrzystanekWlini związanych z PrzystanekWlini.
     */
    @OneToMany(mappedBy = "przystanekWlini")
    private List<KursPrzystanekWlini> kursPrzystanekWlinii;

    // Getters

    /**
     * Zwraca identyfikator PrzystanekWlini.
     * @return przystanekWliniId Identyfikator PrzystanekWlini.
     */
    public Long getPrzystanekWliniId() {
        return przystanekWliniId;
    }

    /**
     * Zwraca przystanek związany z PrzystanekWlini.
     * @return przystanek Przystanek związany z PrzystanekWlini.
     */
    public Przystanek getPrzystanek() {
        return przystanek;
    }

    /**
     * Zwraca linię związaną z PrzystanekWlini.
     * @return linia Linia związana z PrzystanekWlini.
     */
    public Linia getLinia() {
        return linia;
    }

    /**
     * Zwraca kolejność przystanku w linii.
     * @return kolejnosc Kolejność przystanku w linii.
     */
    public Short getKolejnosc() {
        return kolejnosc;
    }

    /**
     * Zwraca listę KursPrzystanekWlini związanych z PrzystanekWlini.
     * @return kursPrzystanekWlinii Lista KursPrzystanekWlini.
     */
    public List<KursPrzystanekWlini> getKursPrzystanekWlinii() {
        return kursPrzystanekWlinii;
    }

    // Setters

    /**
     * Ustawia identyfikator PrzystanekWlini.
     * @param przystanekWliniId Identyfikator PrzystanekWlini.
     */
    public void setPrzystanekWliniId(Long przystanekWliniId) {
        this.przystanekWliniId = przystanekWliniId;
    }

    /**
     * Ustawia przystanek związany z PrzystanekWlini.
     * @param przystanek Przystanek związany z PrzystanekWlini.
     */
    public void setPrzystanek(Przystanek przystanek) {
        this.przystanek = przystanek;
    }

    /**
     * Ustawia linię związaną z PrzystanekWlini.
     * @param linia Linia związana z PrzystanekWlini.
     */
    public void setLinia(Linia linia) {
        this.linia = linia;
    }

    /**
     * Ustawia kolejność przystanku w linii.
     * @param kolejnosc Kolejność przystanku w linii.
     */
    public void setKolejnosc(Short kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    /**
     * Ustawia listę KursPrzystanekWlini związanych z PrzystanekWlini.
     * @param kursPrzystanekWlinii Lista KursPrzystanekWlini.
     */
    public void setKursPrzystanekWlinii(List<KursPrzystanekWlini> kursPrzystanekWlinii) {
        this.kursPrzystanekWlinii = kursPrzystanekWlinii;
    }
}
