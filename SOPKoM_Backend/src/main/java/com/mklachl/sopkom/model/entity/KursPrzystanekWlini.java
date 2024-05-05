package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "kursprzystanekwlini")
public class KursPrzystanekWlini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kursprzystankiwlini_id")
    private Long kursPrzystanekWliniId;

    @ManyToOne
    @JoinColumn(name = "kurs_id", referencedColumnName = "kurs_id")
    private Kurs kurs;

    @ManyToOne
    @JoinColumn(name = "przystanekwlini_id", referencedColumnName = "przystanekwlini_id")
    private PrzystanekWlini przystanekWlini;

    @Column(name = "godzinna", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date godzinna;

    // Getters
    public Long getKursPrzystanekWliniId() {
        return kursPrzystanekWliniId;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public PrzystanekWlini getPrzystanekWlini() {
        return przystanekWlini;
    }

    public Date getGodzinna() {
        return godzinna;
    }

    // Setters
    public void setKursPrzystanekWliniId(Long kursPrzystanekWliniId) {
        this.kursPrzystanekWliniId = kursPrzystanekWliniId;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public void setPrzystanekWlini(PrzystanekWlini przystanekWlini) {
        this.przystanekWlini = przystanekWlini;
    }

    public void setGodzinna(Date godzinna) {
        this.godzinna = godzinna;
    }
}