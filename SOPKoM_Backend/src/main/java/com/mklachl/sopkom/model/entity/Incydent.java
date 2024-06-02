package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "incydenty")
public class Incydent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incydent_id")
    private Long incydentId;

    @Column(name = "typ", nullable = false, length = 50)
    private String typ;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "autobus_id", referencedColumnName = "autbous_id")
    private Autobus autobus;

    @ManyToOne
    @JoinColumn(name = "kierowca_id", referencedColumnName = "kierowca_id")
    private Kierowca kierowca;

    @ManyToOne
    @JoinColumn(name = "przejazd_id", referencedColumnName = "przejazd_id")
    private Przejazd przejazd;

    @Column(name = "krotko", length = 60)
    private String krotko;
    
    @Column(name = "dodatkowe_informacje", length = 800)
    private String dodatkoweInformacje;

    @Column(name = "koszty")
    private float koszty;

    // Getters
    public Long getIncydentId() {
        return incydentId;
    }

    public String getTyp() {
        return typ;
    }

    public Date getDate() {
        return date;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public Kierowca getKierowca() {
        return kierowca;
    }

    public Przejazd getPrzejazd() {
        return przejazd;
    }

    public String getDodatkoweInformacje() {
        return dodatkoweInformacje;
    }

    public float getKoszty() {
        return koszty;
    }

    // Setters
    public void setIncydentId(Long incydentId) {
        this.incydentId = incydentId;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public void setKierowca(Kierowca kierowca) {
        this.kierowca = kierowca;
    }

    public void setPrzejazd(Przejazd przejazd) {
        this.przejazd = przejazd;
    }

    public void setDodatkoweInformacje(String dodatkoweInformacje) {
        this.dodatkoweInformacje = dodatkoweInformacje;
    }

    public void setKoszty(float koszty) {
        this.koszty = koszty;
    }

	public String getKrotko() {
		return krotko;
	}

	public void setKrotko(String krotko) {
		this.krotko = krotko;
	}
    
    
}