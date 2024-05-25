package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.PrzejazdBilet;

public class PrzejazdBiletDto {
    private Long przejazdBiletId;
    private Long przejazdId;
    private Short biletId;
    private float cenaBiletu;

    public PrzejazdBiletDto() {}

    public PrzejazdBiletDto(PrzejazdBilet przejazdBilet) {
        this.przejazdBiletId = przejazdBilet.getPrzejazdBiletId();
        this.przejazdId = przejazdBilet.getPrzejazd().getPrzejazdId();
        this.biletId = przejazdBilet.getBilet().getBiletId();
        this.cenaBiletu = przejazdBilet.getCenaBiletu();
    }

    // Getters and Setters
    public Long getPrzejazdBiletId() {
        return przejazdBiletId;
    }

    public void setPrzejazdBiletId(Long przejazdBiletId) {
        this.przejazdBiletId = przejazdBiletId;
    }

    public Long getPrzejazdId() {
        return przejazdId;
    }

    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    public Short getBiletId() {
        return biletId;
    }

    public void setBiletId(Short biletId) {
        this.biletId = biletId;
    }

    public float getCenaBiletu() {
        return cenaBiletu;
    }

    public void setCenaBiletu(float cenaBiletu) {
        this.cenaBiletu = cenaBiletu;
    }
}
