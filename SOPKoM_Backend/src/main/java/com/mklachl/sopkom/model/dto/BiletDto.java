package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Bilet;

public class BiletDto {
    private Short biletId;
    private float cena;
    private String nazwa;

    public BiletDto() {}

    public BiletDto(Bilet bilet) {
        this.biletId = bilet.getBiletId();
        this.cena = bilet.getCena();
        this.nazwa = bilet.getNazwa();
    }

    // Getters and Setters
    public Short getBiletId() {
        return biletId;
    }

    public void setBiletId(Short biletId) {
        this.biletId = biletId;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}