package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Klasa reprezentująca encję Linia.
 */
@Entity
@Table(name = "linie")
public class Linia {

    /**
     * Identyfikator linii.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linia_id")
    private Long liniaId;

    /**
     * Numer linii.
     */
    @Column(name = "numer", nullable = false, length = 50)
    private String numer;

    /**
     * Lista przystanków w linii.
     */
    @OneToMany(mappedBy = "linia")
    private List<PrzystanekWlini> przystankiWlini;

    /**
     * Sprawdza, czy linia jest odwracalna.
     * @return boolean Wartość true, jeśli linia jest odwracalna, w przeciwnym razie false.
     */
    @Transient
    public boolean odwracalna() {
        for (PrzystanekWlini przystanekWlini : this.przystankiWlini) {
            Przystanek przystanek = przystanekWlini.getPrzystanek();
            if (przystanek.getPrzystanekOdwrotny() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Zwraca identyfikator linii.
     * @return liniaId Identyfikator linii.
     */
    public Long getLiniaId() {
        return liniaId;
    }

    /**
     * Ustawia identyfikator linii.
     * @param liniaId Identyfikator linii.
     */
    public void setLiniaId(Long liniaId) {
        this.liniaId = liniaId;
    }

    /**
     * Zwraca numer linii.
     * @return numer Numer linii.
     */
    public String getNumer() {
        return numer;
    }

    /**
     * Ustawia numer linii.
     * @param numer Numer linii.
     */
    public void setNumer(String numer) {
        this.numer = numer;
    }

    /**
     * Zwraca listę przystanków w linii.
     * @return przystankiWlini Lista przystanków w linii.
     */
    public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }

    /**
     * Ustawia listę przystanków w linii.
     * @param przystankiWlini Lista przystanków w linii.
     */
    public void setPrzystankiWlini(List<PrzystanekWlini> przystankiWlini) {
        this.przystankiWlini = przystankiWlini;
    }
}
