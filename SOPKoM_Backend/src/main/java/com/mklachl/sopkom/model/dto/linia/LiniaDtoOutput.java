package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.entity.Linia;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) wyjściowy dla encji Linia.
 */
public class LiniaDtoOutput extends LiniaDto {

    /**
     * Flaga określająca, czy linia jest odwracalna.
     */
    protected boolean odwracalna;

    /**
     * Lista przystanków związanych z linią.
     */
    private List<PrzystanekDtoDlaLinia> przystanki;

    /**
     * Konstruktor domyślny.
     */
    public LiniaDtoOutput() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Linia.
     * @param linia Obiekt encji Linia.
     */
    public LiniaDtoOutput(Linia linia) {
        super(linia);
        this.przystanki = new ArrayList<>();
        this.odwracalna = true;
        
        for (int i = 0; i < linia.getPrzystankiWlini().size(); i++) {
            PrzystanekDtoDlaLinia dto = new PrzystanekDtoDlaLinia(linia.getPrzystankiWlini().get(i));
            this.przystanki.add(dto);
            
            if (dto.getPrzystanekOdwrotny() == null) {
                this.odwracalna = false;
            }
        }
    }

    // Getters and Setters

    /**
     * Zwraca wartość flagi określającej, czy linia jest odwracalna.
     * @return odwracalna Wartość flagi odwracalności linii.
     */
    public boolean isOdwracalna() {
        return odwracalna;
    }

    /**
     * Ustawia wartość flagi określającej, czy linia jest odwracalna.
     * @param odwracalna Wartość flagi odwracalności linii.
     */
    public void setOdwracalna(boolean odwracalna) {
        this.odwracalna = odwracalna;
    }

    /**
     * Zwraca listę przystanków związanych z linią.
     * @return przystanki Lista przystanków związanych z linią.
     */
    public List<PrzystanekDtoDlaLinia> getPrzystanki() {
        return przystanki;
    }

    /**
     * Ustawia listę przystanków związanych z linią.
     * @param przystanki Lista przystanków związanych z linią.
     */
    public void setPrzystanki(List<PrzystanekDtoDlaLinia> przystanki) {
        this.przystanki = przystanki;
    }
}
