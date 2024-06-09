package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca encję Incydent.
 */
@Entity
@Table(name = "incydenty")
public class Incydent {

    /**
     * Identyfikator incydentu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incydent_id")
    private Long incydentId;

    /**
     * Typ incydentu.
     */
    @Column(name = "typ", nullable = false, length = 50)
    private String typ;

    /**
     * Data incydentu.
     */
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Autobus związany z incydentem.
     */
    @ManyToOne
    @JoinColumn(name = "autobus_id", referencedColumnName = "autbous_id")
    private Autobus autobus;

    /**
     * Kierowca związany z incydentem.
     */
    @ManyToOne
    @JoinColumn(name = "kierowca_id", referencedColumnName = "kierowca_id")
    private Kierowca kierowca;

    /**
     * Przejazd związany z incydentem.
     */
    @ManyToOne
    @JoinColumn(name = "przejazd_id", referencedColumnName = "przejazd_id")
    private Przejazd przejazd;

    /**
     * Krótki opis incydentu.
     */
    @Column(name = "krotko", length = 60)
    private String krotko;

    /**
     * Dodatkowe informacje dotyczące incydentu.
     */
    @Column(name = "dodatkowe_informacje", length = 800)
    private String dodatkoweInformacje;

    /**
     * Koszty związane z incydentem.
     */
    @Column(name = "koszty")
    private float koszty;

    /**
     * Zwraca identyfikator incydentu.
     * @return incydentId Identyfikator incydentu.
     */
    public Long getIncydentId() {
        return incydentId;
    }

    /**
     * Ustawia identyfikator incydentu.
     * @param incydentId Identyfikator incydentu.
     */
    public void setIncydentId(Long incydentId) {
        this.incydentId = incydentId;
    }

    /**
     * Zwraca typ incydentu.
     * @return typ Typ incydentu.
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Ustawia typ incydentu.
     * @param typ Typ incydentu.
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * Zwraca datę incydentu.
     * @return date Data incydentu.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Ustawia datę incydentu.
     * @param date Data incydentu.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Zwraca autobus związany z incydentem.
     * @return autobus Autobus związany z incydentem.
     */
    public Autobus getAutobus() {
        return autobus;
    }

    /**
     * Ustawia autobus związany z incydentem.
     * @param autobus Autobus związany z incydentem.
     */
    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    /**
     * Zwraca kierowcę związanego z incydentem.
     * @return kierowca Kierowca związany z incydentem.
     */
    public Kierowca getKierowca() {
        return kierowca;
    }

    /**
     * Ustawia kierowcę związanego z incydentem.
     * @param kierowca Kierowca związany z incydentem.
     */
    public void setKierowca(Kierowca kierowca) {
        this.kierowca = kierowca;
    }

    /**
     * Zwraca przejazd związany z incydentem.
     * @return przejazd Przejazd związany z incydentem.
     */
    public Przejazd getPrzejazd() {
        return przejazd;
    }

    /**
     * Ustawia przejazd związany z incydentem.
     * @param przejazd Przejazd związany z incydentem.
     */
    public void setPrzejazd(Przejazd przejazd) {
        this.przejazd = przejazd;
    }

    /**
     * Zwraca krótki opis incydentu.
     * @return krotko Krótki opis incydentu.
     */
    public String getKrotko() {
        return krotko;
    }

    /**
     * Ustawia krótki opis incydentu.
     * @param krotko Krótki opis incydentu.
     */
    public void setKrotko(String krotko) {
        this.krotko = krotko;
    }

    /**
     * Zwraca dodatkowe informacje dotyczące incydentu.
     * @return dodatkoweInformacje Dodatkowe informacje.
     */
    public String getDodatkoweInformacje() {
        return dodatkoweInformacje;
    }

    /**
     * Ustawia dodatkowe informacje dotyczące incydentu.
     * @param dodatkoweInformacje Dodatkowe informacje.
     */
    public void setDodatkoweInformacje(String dodatkoweInformacje) {
        this.dodatkoweInformacje = dodatkoweInformacje;
    }

    /**
     * Zwraca koszty związane z incydentem.
     * @return koszty Koszty związane z incydentem.
     */
    public float getKoszty() {
        return koszty;
    }

    /**
     * Ustawia koszty związane z incydentem.
     * @param koszty Koszty związane z incydentem.
     */
    public void setKoszty(float koszty) {
        this.koszty = koszty;
    }
}
