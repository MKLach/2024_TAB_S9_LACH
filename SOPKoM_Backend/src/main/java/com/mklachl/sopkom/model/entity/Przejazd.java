package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Klasa reprezentująca encję Przejazd.
 */
@Entity
@Table(name = "przejazdy")
public class Przejazd {

    /**
     * Identyfikator przejazdu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przejazd_id")
    private Long przejazdId;

    /**
     * Kierowca związany z przejazdem.
     */
    @ManyToOne
    @JoinColumn(name = "kierowca_id", referencedColumnName = "kierowca_id")
    private Kierowca kierowca;

    /**
     * Autobus używany w przejeździe.
     */
    @ManyToOne
    @JoinColumn(name = "autobus_id", referencedColumnName = "autbous_id")
    private Autobus autobus;

    /**
     * Kurs związany z przejazdem.
     */
    @ManyToOne
    @JoinColumn(name = "kurs_id", referencedColumnName = "kurs_id")
    private Kurs kurs;

    /**
     * Spalanie autobusu podczas przejazdu.
     */
    @Column(name = "spalanie", nullable = false)
    private float spalanie;

    /**
     * Cena za litr paliwa podczas przejazdu.
     */
    @Column(name = "cena_za_litr", nullable = false)
    private float cenaZaLitr;

    /**
     * Długość trasy przejazdu.
     */
    @Column(name = "dlugosc_trasy", nullable = false)
    private float dlugoscTrasy;

    /**
     * Data rozpoczęcia przejazdu.
     */
    @Column(name = "data_startu", nullable = true)
    private Date dataStartu;

    /**
     * Data zakończenia przejazdu.
     */
    @Column(name = "data_konca", nullable = true)
    private Date dataKonca;

    /**
     * Data przejazdu.
     */
    @Column(name = "data", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date data;

    /**
     * Lista przystanków w linii związanych z przejazdem.
     */
    @OneToMany(mappedBy = "przejazd")
    private List<PrzejazdKursPrzystanekWlini> przejazdKursPrzystanekWlini;

    /**
     * Lista biletów związanych z przejazdem.
     */
    @OneToMany(mappedBy = "przejazd")
    private List<PrzejazdBilet> PrzejazdBilet;

    /**
     * Zwraca identyfikator przejazdu.
     * @return przejazdId Identyfikator przejazdu.
     */
    public Long getPrzejazdId() {
        return przejazdId;
    }

    /**
     * Ustawia identyfikator przejazdu.
     * @param przejazdId Identyfikator przejazdu.
     */
    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    /**
     * Zwraca kierowcę związanego z przejazdem.
     * @return kierowca Kierowca związany z przejazdem.
     */
    public Kierowca getKierowca() {
        return kierowca;
    }

    /**
     * Ustawia kierowcę związanego z przejazdem.
     * @param kierowca Kierowca związany z przejazdem.
     */
    public void setKierowca(Kierowca kierowca) {
        this.kierowca = kierowca;
    }

    /**
     * Zwraca autobus używany w przejeździe.
     * @return autobus Autobus używany w przejeździe.
     */
    public Autobus getAutobus() {
        return autobus;
    }

    /**
     * Ustawia autobus używany w przejeździe.
     * @param autobus Autobus używany w przejeździe.
     */
    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    /**
     * Zwraca kurs związany z przejazdem.
     * @return kurs Kurs związany z przejazdem.
     */
    public Kurs getKurs() {
        return kurs;
    }

    /**
     * Ustawia kurs związany z przejazdem.
     * @param kurs Kurs związany z przejazdem.
     */
    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    /**
     * Zwraca spalanie autobusu podczas przejazdu.
     * @return spalanie Spalanie autobusu podczas przejazdu.
     */
    public float getSpalanie() {
        return spalanie;
    }

    /**
     * Ustawia spalanie autobusu podczas przejazdu.
     * @param spalanie Spalanie autobusu podczas przejazdu.
     */
    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    /**
     * Zwraca cenę za litr paliwa podczas przejazdu.
     * @return cenaZaLitr Cena za litr paliwa podczas przejazdu.
     */
    public float getCenaZaLitr() {
        return cenaZaLitr;
    }

    /**
     * Ustawia cenę za litr paliwa podczas przejazdu.
     * @param cenaZaLitr Cena za litr paliwa podczas przejazdu.
     */
    public void setCenaZaLitr(float cenaZaLitr) {
        this.cenaZaLitr = cenaZaLitr;
    }

    /**
     * Zwraca długość trasy przejazdu.
     * @return dlugoscTrasy Długość trasy przejazdu.
     */
    public float getDlugoscTrasy() {
        return dlugoscTrasy;
    }

    /**
     * Ustawia długość trasy przejazdu.
     * @param dlugoscTrasy Długość trasy przejazdu.
     */
    public void setDlugoscTrasy(float dlugoscTrasy) {
        this.dlugoscTrasy = dlugoscTrasy;
    }

    /**
     * Zwraca datę rozpoczęcia przejazdu.
     * @return dataStartu Data rozpoczęcia przejazdu.
     */
    public Date getDataStartu() {
        return dataStartu;
    }

    /**
     * Ustawia datę rozpoczęcia przejazdu.
     * @param dataStartu Data rozpoczęcia przejazdu.
     */
    public void setDataStartu(Date dataStartu) {
        this.dataStartu = dataStartu;
    }

    /**
     * Zwraca datę zakończenia przejazdu.
     * @return dataKonca Data zakończenia przejazdu.
     */
    public Date getDataKonca() {
        return dataKonca;
    }

    /**
     * Ustawia datę zakończenia przejazdu.
     * @param dataKonca Data zakończenia przejazdu.
     */
    public void setDataKonca(Date dataKonca) {
        this.dataKonca = dataKonca;
    }

    /**
     * Zwraca datę przejazdu.
     * @return data Data przejazdu.
     */
    public Date getData() {
        return data;
    }

    /**
     * Ustawia datę przejazdu.
     * @param data Data przejazdu.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Zwraca listę przystanków w linii związanych z przejazdem.
     * @return przejazdKursPrzystanekWlini Lista przystanków w linii związanych z przejazdem.
     */
    public List<PrzejazdKursPrzystanekWlini> getPrzejazdKursPrzystanekWlini() {
        return przejazdKursPrzystanekWlini;
    }

    /**
     * Ustawia listę przystanków w linii związanych z przejazdem.
     * @param przejazdKursPrzystanekWlini Lista przystanków w linii związanych z przejazdem.
     */
    public void setPrzejazdKursPrzystanekWlini(List<PrzejazdKursPrzystanekWlini> przejazdKursPrzystanekWlini) {
        this.przejazdKursPrzystanekWlini = przejazdKursPrzystanekWlini;
    }

    /**
     * Zwraca listę biletów związanych z przejazdem.
     * @return PrzejazdBilet Lista biletów związanych z przejazdem.
     */
    public List<PrzejazdBilet> getPrzejazdBilet() {
        return PrzejazdBilet;
    }

    /**
     * Ustawia listę biletów związanych z przejazdem.
     * @param PrzejazdBilet Lista biletów związanych z przejazdem.
     */
    public void setPrzejazdBilet(List<PrzejazdBilet> PrzejazdBilet) {
        this.PrzejazdBilet = PrzejazdBilet;
    }
}
