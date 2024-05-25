package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;
import java.util.Date;

public class PrzejazdKursPrzystanekWliniDto {
    private Long przejazdKursPrzystanekWliniId;
    private int kolejnosc;
    private Date przewidywanaGodzinna;
    private Date realnaGodzinna;
    private String nazwa;
    private Long przystanekId;

    public PrzejazdKursPrzystanekWliniDto() {}

    public PrzejazdKursPrzystanekWliniDto(PrzejazdKursPrzystanekWlini przejazdKursPrzystanekWlini) {
        this.przejazdKursPrzystanekWliniId = przejazdKursPrzystanekWlini.getPrzejazdKursPrzystanekWliniId();
        this.kolejnosc = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getPrzystanekWlini().getKolejnosc();
        this.przewidywanaGodzinna = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getGodzinna();
        this.realnaGodzinna = przejazdKursPrzystanekWlini.getRealnaGodzinna();
        this.nazwa = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getPrzystanekWlini().getPrzystanek().getNazwa();
        this.przystanekId = przejazdKursPrzystanekWlini.getKursPrzystanekWlini().getPrzystanekWlini().getPrzystanek().getPrzystanekId();
    }

    // Getters and Setters
    public Long getPrzejazdKursPrzystanekWliniId() {
        return przejazdKursPrzystanekWliniId;
    }

    public void setPrzejazdKursPrzystanekWliniId(Long przejazdKursPrzystanekWliniId) {
        this.przejazdKursPrzystanekWliniId = przejazdKursPrzystanekWliniId;
    }

    public int getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(int kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public Date getPrzewidywanaGodzinna() {
        return przewidywanaGodzinna;
    }

    public void setPrzewidywanaGodzinna(Date przewidywanaGodzinna) {
        this.przewidywanaGodzinna = przewidywanaGodzinna;
    }

    public Date getRealnaGodzinna() {
        return realnaGodzinna;
    }

    public void setRealnaGodzinna(Date realnaGodzinna) {
        this.realnaGodzinna = realnaGodzinna;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Long getPrzystanekId() {
        return przystanekId;
    }

    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }
}
