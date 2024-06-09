package com.mklachl.sopkom.model.entity;

import java.util.Date;
import java.util.Objects;
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

/**
 * Klasa reprezentująca encję Kierowca.
 */
@Entity
@Table(name = "kierowcy")
public class Kierowca {

    /**
     * Identyfikator kierowcy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kierowca_id")
    private Long kierowcaId;

    /**
     * Data ważności prawa jazdy kierowcy.
     */
    @Column(name = "prawojazdywaznedo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prawoJazdyWazneDo;

    /**
     * Imię kierowcy.
     */
    @Column(name = "imie", nullable = false, length = 30)
    private String imie;

    /**
     * Nazwisko kierowcy.
     */
    @Column(name = "nazwisko", nullable = false, length = 30)
    private String nazwisko;

    /**
     * PESEL kierowcy.
     */
    @Column(name = "pesel", nullable = false, length = 30)
    private String pesel;

    /**
     * Użytkownik związany z kierowcą.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * Zwraca identyfikator kierowcy.
     * @return kierowcaId Identyfikator kierowcy.
     */
    public Long getKierowcaId() {
        return kierowcaId;
    }

    /**
     * Ustawia identyfikator kierowcy.
     * @param kierowcaId Identyfikator kierowcy.
     */
    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    /**
     * Zwraca datę ważności prawa jazdy kierowcy.
     * @return prawoJazdyWazneDo Data ważności prawa jazdy kierowcy.
     */
    public Date getPrawoJazdyWazneDo() {
        return prawoJazdyWazneDo;
    }

    /**
     * Ustawia datę ważności prawa jazdy kierowcy.
     * @param prawoJazdyWazneDo Data ważności prawa jazdy kierowcy.
     */
    public void setPrawoJazdyWazneDo(Date prawoJazdyWazneDo) {
        this.prawoJazdyWazneDo = prawoJazdyWazneDo;
    }

    /**
     * Zwraca imię kierowcy.
     * @return imie Imię kierowcy.
     */
    public String getImie() {
        return imie;
    }

    /**
     * Ustawia imię kierowcy.
     * @param imie Imię kierowcy.
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * Zwraca nazwisko kierowcy.
     * @return nazwisko Nazwisko kierowcy.
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Ustawia nazwisko kierowcy.
     * @param nazwisko Nazwisko kierowcy.
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * Zwraca PESEL kierowcy.
     * @return pesel PESEL kierowcy.
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Ustawia PESEL kierowcy.
     * @param pesel PESEL kierowcy.
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Zwraca użytkownika związanego z kierowcą.
     * @return user Użytkownik związany z kierowcą.
     */
    public User getUser() {
        return user;
    }

    /**
     * Ustawia użytkownika związanego z kierowcą.
     * @param user Użytkownik związany z kierowcą.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Generuje hash code dla obiektu Kierowca.
     * @return hashCode Hash code obiektu Kierowca.
     */
    @Override
    public int hashCode() {
        return Objects.hash(imie, kierowcaId, nazwisko, pesel, prawoJazdyWazneDo, user);
    }

    /**
     * Porównuje bieżący obiekt Kierowca z innym obiektem.
     * @param obj Obiekt do porównania.
     * @return boolean Wartość true, jeśli obiekty są równe, w przeciwnym razie false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Kierowca other = (Kierowca) obj;
        return Objects.equals(imie, other.imie) && Objects.equals(kierowcaId, other.kierowcaId)
                && Objects.equals(nazwisko, other.nazwisko) && Objects.equals(pesel, other.pesel)
                && Objects.equals(prawoJazdyWazneDo, other.prawoJazdyWazneDo);
    }
}
