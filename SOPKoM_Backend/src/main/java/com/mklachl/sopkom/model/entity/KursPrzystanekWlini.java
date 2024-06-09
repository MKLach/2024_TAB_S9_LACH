package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca encję KursPrzystanekWlini.
 */
@Entity
@Table(name = "kursprzystanekwlini")
public class KursPrzystanekWlini {

    /**
     * Identyfikator KursPrzystanekWlini.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kursprzystankiwlini_id")
    private Long kursPrzystanekWliniId;

    /**
     * Kurs związany z KursPrzystanekWlini.
     */
    @ManyToOne
    @JoinColumn(name = "kurs_id", referencedColumnName = "kurs_id")
    private Kurs kurs;

    /**
     * Przystanek w linii związany z KursPrzystanekWlini.
     */
    @ManyToOne
    @JoinColumn(name = "przystanekwlini_id", referencedColumnName = "przystanekwlini_id")
    private PrzystanekWlini przystanekWlini;

    /**
     * Godzina przystanku w linii.
     */
    @Column(name = "godzinna", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date godzinna;

    /**
     * Zwraca identyfikator KursPrzystanekWlini.
     * @return kursPrzystanekWliniId Identyfikator KursPrzystanekWlini.
     */
    public Long getKursPrzystanekWliniId() {
        return kursPrzystanekWliniId;
    }

    /**
     * Ustawia identyfikator KursPrzystanekWlini.
     * @param kursPrzystanekWliniId Identyfikator KursPrzystanekWlini.
     */
    public void setKursPrzystanekWliniId(Long kursPrzystanekWliniId) {
        this.kursPrzystanekWliniId = kursPrzystanekWliniId;
    }

    /**
     * Zwraca kurs związany z KursPrzystanekWlini.
     * @return kurs Kurs związany z KursPrzystanekWlini.
     */
    public Kurs getKurs() {
        return kurs;
    }

    /**
     * Ustawia kurs związany z KursPrzystanekWlini.
     * @param kurs Kurs związany z KursPrzystanekWlini.
     */
    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    /**
     * Zwraca przystanek w linii związany z KursPrzystanekWlini.
     * @return przystanekWlini Przystanek w linii.
     */
    public PrzystanekWlini getPrzystanekWlini() {
        return przystanekWlini;
    }

    /**
     * Ustawia przystanek w linii związany z KursPrzystanekWlini.
     * @param przystanekWlini Przystanek w linii.
     */
    public void setPrzystanekWlini(PrzystanekWlini przystanekWlini) {
        this.przystanekWlini = przystanekWlini;
    }

    /**
     * Zwraca godzinę przystanku w linii.
     * @return godzinna Godzina przystanku w linii.
     */
    public Date getGodzinna() {
        return godzinna;
    }

    /**
     * Ustawia godzinę przystanku w linii.
     * @param godzinna Godzina przystanku w linii.
     */
    public void setGodzinna(Date godzinna) {
        this.godzinna = godzinna;
    }
}
