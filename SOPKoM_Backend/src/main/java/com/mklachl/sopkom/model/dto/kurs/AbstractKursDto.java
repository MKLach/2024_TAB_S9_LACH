package com.mklachl.sopkom.model.dto.kurs;

import com.mklachl.sopkom.model.entity.Kurs;

/**
 * Abstrakcyjna klasa Data Transfer Object (DTO) dla encji Kurs.
 */
public abstract class AbstractKursDto {

    /**
     * Identyfikator kursu.
     */
    protected Long kursId;

    /**
     * Identyfikator linii związanej z kursem.
     */
    protected Long liniaId;

    /**
     * Identyfikator harmonogramu związanego z kursem.
     */
    protected Short harmonogramId;

    /**
     * Kierunek kursu.
     */
    protected Short kierunek;

    /**
     * Typ autobusu używanego w kursie.
     */
    protected Short typ_autobusu;

    /**
     * Konstruktor przyjmujący obiekt encji Kurs.
     * @param kurs Obiekt encji Kurs.
     */
    public AbstractKursDto(Kurs kurs) {
        this.setKursId(kurs.getKursId());
        this.setKierunek(kurs.getKierunek());
        this.setTyp_autobusu(kurs.getTypAutobusu());
    }

    /**
     * Konstruktor domyślny.
     */
    public AbstractKursDto() {
    }

    // Getters and Setters

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
     * Zwraca identyfikator linii związanej z kursem.
     * @return liniaId Identyfikator linii.
     */
    public Long getLiniaId() {
        return liniaId;
    }

    /**
     * Ustawia identyfikator linii związanej z kursem.
     * @param liniaId Identyfikator linii.
     */
    public void setLiniaId(Long liniaId) {
        this.liniaId = liniaId;
    }

    /**
     * Zwraca identyfikator harmonogramu związanego z kursem.
     * @return harmonogramId Identyfikator harmonogramu.
     */
    public Short getHarmonogramId() {
        return harmonogramId;
    }

    /**
     * Ustawia identyfikator harmonogramu związanego z kursem.
     * @param harmonogramId Identyfikator harmonogramu.
     */
    public void setHarmonogramId(Short harmonogramId) {
        this.harmonogramId = harmonogramId;
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
     * Zwraca typ autobusu używanego w kursie.
     * @return typ_autobusu Typ autobusu.
     */
    public Short getTyp_autobusu() {
        return typ_autobusu;
    }

    /**
     * Ustawia typ autobusu używanego w kursie.
     * @param typ_autobusu Typ autobusu.
     */
    public void setTyp_autobusu(Short typ_autobusu) {
        this.typ_autobusu = typ_autobusu;
    }
}
