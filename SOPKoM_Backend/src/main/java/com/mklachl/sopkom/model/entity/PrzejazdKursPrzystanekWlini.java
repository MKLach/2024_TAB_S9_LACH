package com.mklachl.sopkom.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;

@Entity
@Table(name = "przejazdkursprzystanekwlini")
public class PrzejazdKursPrzystanekWlini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "przejazdkursprzystanekwlini_id")
    private Long przejazdKursPrzystanekWliniId;

    @ManyToOne
    @JoinColumn(name = "przejazd_id", referencedColumnName = "przejazd_id")
    private Przejazd przejazd;

    @ManyToOne
    @JoinColumn(name = "kursprzystanekwlini_id", referencedColumnName = "kursprzystankiwlini_id")
    private KursPrzystanekWlini kursPrzystanekWlini;

    @Column(name = "realnagodzinna")
    private Date realnaGodzinna;

    // Getters
    public Long getPrzejazdKursPrzystanekWliniId() {
        return przejazdKursPrzystanekWliniId;
    }

    public Przejazd getPrzejazd() {
        return przejazd;
    }

    public KursPrzystanekWlini getKursPrzystanekWlini() {
        return kursPrzystanekWlini;
    }

    public Date getRealnaGodzinna() {
        return realnaGodzinna;
    }

    // Setters
    public void setPrzejazdKursPrzystanekWliniId(Long przejazdKursPrzystanekWliniId) {
        this.przejazdKursPrzystanekWliniId = przejazdKursPrzystanekWliniId;
    }

    public void setPrzejazd(Przejazd przejazd) {
        this.przejazd = przejazd;
    }

    public void setKursPrzystanekWlini(KursPrzystanekWlini kursPrzystanekWlini) {
        this.kursPrzystanekWlini = kursPrzystanekWlini;
    }

    public void setRealnaGodzinna(Date realnaGodzinna) {
        this.realnaGodzinna = realnaGodzinna;
    }
}