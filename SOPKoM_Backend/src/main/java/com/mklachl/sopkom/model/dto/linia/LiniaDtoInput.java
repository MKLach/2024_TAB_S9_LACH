package com.mklachl.sopkom.model.dto.linia;

import com.mklachl.sopkom.model.entity.Linia;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) wejściowy dla encji Linia.
 */
public class LiniaDtoInput extends LiniaDto {

    /**
     * Lista przystanków związanych z linią.
     */
    private List<PrzystanekDtoDlaLiniaInput> przystanki;

    /**
     * Konstruktor domyślny.
     */
    public LiniaDtoInput() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Linia.
     * @param linia Obiekt encji Linia.
     */
    public LiniaDtoInput(Linia linia) {
        super(linia);
        this.przystanki = new ArrayList<>();
        
        for (int i = 0; i < linia.getPrzystankiWlini().size(); i++) {
            PrzystanekDtoDlaLiniaInput dto = new PrzystanekDtoDlaLiniaInput(linia.getPrzystankiWlini().get(i));
            this.przystanki.add(dto);
        }
    }

    // Getters and Setters

    /**
     * Zwraca listę przystanków związanych z linią.
     * @return przystanki Lista przystanków związanych z linią.
     */
    public List<PrzystanekDtoDlaLiniaInput> getPrzystanki() {
        return przystanki;
    }

    /**
     * Ustawia listę przystanków związanych z linią.
     * @param przystanki Lista przystanków związanych z linią.
     */
    public void setPrzystanki(List<PrzystanekDtoDlaLiniaInput> przystanki) {
        this.przystanki = przystanki;
    }
}
