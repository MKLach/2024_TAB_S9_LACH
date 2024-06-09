package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;
import java.util.Date;

/**
 * Data Transfer Object (DTO) dla encji Kierowca.
 */
public class KierowcaDto {

    /**
     * Identyfikator kierowcy.
     */
    private Long kierowcaId;

    /**
     * Data ważności prawa jazdy kierowcy.
     */
    private Date prawoJazdyWazneDo;

    /**
     * Imię kierowcy.
     */
    private String imie;

    /**
     * Nazwisko kierowcy.
     */
    private String nazwisko;

    /**
     * PESEL kierowcy.
     */
    private String pesel;

    /**
     * DTO użytkownika związanego z kierowcą.
     */
    private UserDto user;

    /**
     * Konstruktor domyślny.
     */
    public KierowcaDto() {}

    /**
     * Konstruktor przyjmujący obiekt encji Kierowca.
     * @param kierowca Obiekt encji Kierowca.
     */
    public KierowcaDto(Kierowca kierowca) {
        this.kierowcaId = kierowca.getKierowcaId();
        this.prawoJazdyWazneDo = kierowca.getPrawoJazdyWazneDo();
        this.imie = kierowca.getImie();
        this.nazwisko = kierowca.getNazwisko();
        this.pesel = kierowca.getPesel();
        
        if (kierowca.getUser() != null) {
            this.user = new UserDto(kierowca.getUser());
        }
    }

    // Getters

    /**
     * Zwraca identyfikator kierowcy.
     * @return kierowcaId Identyfikator kierowcy.
     */
    public Long getKierowcaId() {
        return kierowcaId;
    }

    /**
     * Zwraca datę ważności prawa jazdy kierowcy.
     * @return prawoJazdyWazneDo Data ważności prawa jazdy kierowcy.
     */
    public Date getPrawoJazdyWazneDo() {
        return prawoJazdyWazneDo;
    }

    /**
     * Zwraca imię kierowcy.
     * @return imie Imię kierowcy.
     */
    public String getImie() {
        return imie;
    }

    /**
     * Zwraca nazwisko kierowcy.
     * @return nazwisko Nazwisko kierowcy.
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Zwraca PESEL kierowcy.
     * @return pesel PESEL kierowcy.
     */
    public String getPesel() {
        return pesel;
    }

    /**
     * Zwraca DTO użytkownika związanego z kierowcą.
     * @return user DTO użytkownika.
     */
    public UserDto getUser() {
        return user;
    }

    // Setters

    /**
     * Ustawia identyfikator kierowcy.
     * @param kierowcaId Identyfikator kierowcy.
     */
    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    /**
     * Ustawia datę ważności prawa jazdy kierowcy.
     * @param prawoJazdyWazneDo Data ważności prawa jazdy kierowcy.
     */
    public void setPrawoJazdyWazneDo(Date prawoJazdyWazneDo) {
        this.prawoJazdyWazneDo = prawoJazdyWazneDo;
    }

    /**
     * Ustawia imię kierowcy.
     * @param imie Imię kierowcy.
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * Ustawia nazwisko kierowcy.
     * @param nazwisko Nazwisko kierowcy.
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * Ustawia PESEL kierowcy.
     * @param pesel PESEL kierowcy.
     */
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    /**
     * Ustawia DTO użytkownika związanego z kierowcą.
     * @param user DTO użytkownika.
     */
    public void setUserDto(UserDto user) {
        this.user = user;
    }

    /**
     * Zwraca reprezentację obiektu w postaci łańcucha znaków.
     * @return Reprezentacja obiektu w postaci łańcucha znaków.
     */
    @Override
    public String toString() {
        return "KierowcaDto [kierowcaId=" + kierowcaId + ", prawoJazdyWazneDo=" + prawoJazdyWazneDo + ", imie=" + imie
                + ", nazwisko=" + nazwisko + ", pesel=" + pesel + ", user=" + user + "]";
    }
}
