package com.mklachl.sopkom.model.dto;
import com.mklachl.sopkom.model.entity.Autobus;

import java.util.Date;

public class AutobusDto {
    private Long autbousId;

    private String numerRejestracyjny;

    private Date przegladWaznyDo;

    private String status;

    private float przebieg;

    public AutobusDto(){

    }

    public AutobusDto(Autobus autobus) {
        this.autbousId = autobus.getAutbousId();
        this.numerRejestracyjny = autobus.getNumerRejestracyjny();
        this.przegladWaznyDo = autobus.getPrzegladWaznyDo();
        this.status = autobus.getStatus();
        this.przebieg = autobus.getPrzebieg();
    }

    // Getters
    public Long getAutbousId() {
        return autbousId;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public Date getPrzegladWaznyDo() {
        return przegladWaznyDo;
    }

    public String getStatus() {
        return status;
    }

    public float getPrzebieg() {
        return przebieg;
    }

    // Setters
    public void setAutbousId(Long autbousId) {
        this.autbousId = autbousId;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public void setPrzegladWaznyDo(Date przegladWaznyDo) {
        this.przegladWaznyDo = przegladWaznyDo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrzebieg(float przebieg) {
        this.przebieg = przebieg;
    }
}
