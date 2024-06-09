package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca encję PrzejazdKursPrzystanekWlini.
 */
@Entity
@Table(name = "przejazdkursprzystanekwlini")
public class PrzejazdKursPrzystanekWlini {

    /**
     * Identyfikator PrzejazdKursPrzystanekWlini.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przejazdkursprzystanekwlini_id")
    private Long przejazdKursPrzystanekWliniId;

    /**
     * Przejazd związany z KursPrzystanekWlini.
     */
    @ManyToOne
    @JoinColumn(name = "przejazd_id", referencedColumnName = "przejazd_id")
    private Przejazd przejazd;

    /**
     * KursPrzystanekWlini związany z PrzejazdKursPrzystanekWlini.
     */
    @ManyToOne
    @JoinColumn(name = "kursprzystanekwlini_id", referencedColumnName = "kursprzystankiwlini_id")
    private KursPrzystanekWlini kursPrzystanekWlini;

    /**
     * Realna godzina przystanku w linii.
     */
    @Column(name = "realnagodzinna")
    private Date realnaGodzinna;

    /**
     * Zwraca identyfikator PrzejazdKursPrzystanekWlini.
     * @return przejazdKursPrzystanekWliniId Identyfikator PrzejazdKursPrzystanekWlini.
     */
    public Long getPrzejazdKursPrzystanekWliniId() {
        return przejazdKursPrzystanekWliniId;
    }

    /**
     * Ustawia identyfikator PrzejazdKursPrzystanekWlini.
     * @param przejazdKursPrzystanekWliniId Identyfikator PrzejazdKursPrzystanekWlini.
     */
    public void setPrzejazdKursPrzystanekWliniId(Long przejazdKursPrzystanekWliniId) {
        this.przejazdKursPrzystanekWliniId = przejazdKursPrzystanekWliniId;
    }

    /**
     * Zwraca przejazd związany z KursPrzystanekWlini.
     * @return przejazd Przejazd związany z KursPrzystanekWlini.
     */
    public Przejazd getPrzejazd() {
        return przejazd;
    }

    /**
     * Ustawia przejazd związany z KursPrzystanekWlini.
     * @param przejazd Przejazd związany z KursPrzystanekWlini.
     */
    public void setPrzejazd(Przejazd przejazd) {
        this.przejazd = przejazd;
    }

    /**
     * Zwraca KursPrzystanekWlini związany z PrzejazdKursPrzystanekWlini.
     * @return kursPrzystanekWlini KursPrzystanekWlini związany z PrzejazdKursPrzystanekWlini.
     */
    public KursPrzystanekWlini getKursPrzystanekWlini() {
        return kursPrzystanekWlini;
    }

    /**
     * Ustawia KursPrzystanekWlini związany z PrzejazdKursPrzystanekWlini.
     * @param kursPrzystanekWlini KursPrzystanekWlini związany z PrzejazdKursPrzystanekWlini.
     */
    public void setKursPrzystanekWlini(KursPrzystanekWlini kursPrzystanekWlini) {
        this.kursPrzystanekWlini = kursPrzystanekWlini;
    }

    /**
     * Zwraca realną godzinę przystanku w linii.
     * @return realnaGodzinna Realna godzina przystanku w linii.
     */
    public Date getRealnaGodzinna() {
        return realnaGodzinna;
    }

    /**
     * Ustawia realną godzinę przystanku w linii.
     * @param realnaGodzinna Realna godzina przystanku w linii.
     */
    public void setRealnaGodzinna(Date realnaGodzinna) {
        this.realnaGodzinna = realnaGodzinna;
    }
}
